package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.example.myapplication.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding;
    var detail: Detail? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.search.setOnClickListener(View.OnClickListener {
            val title = binding.etInput.text.toString()
            if (TextUtils.isEmpty(title)) {
                Toast.makeText(applicationContext, "please input", Toast.LENGTH_SHORT).show()
                return@OnClickListener
            }
            serach(title)
        })
        binding.detail.setOnClickListener(View.OnClickListener {
            if (detail == null) {
                Toast.makeText(applicationContext, "please search", Toast.LENGTH_SHORT).show()
                return@OnClickListener
            }
            App.instance?.URL = detail?.Poster
            val intent = Intent()
            // 注意这是在其他应用AndroidManifest.xml文件中自定义的一个名
            intent.action = "android.intent.action.NING"
            // 通过添加Intent.CATEGORY_DEFAULT类别，可以确保该Intent能够被系统匹配到最适合的组件来处理
            intent.addCategory(Intent.CATEGORY_DEFAULT)
            startActivity(intent)
        })
        binding.share.setOnClickListener(View.OnClickListener {
            if (detail == null) {
                Toast.makeText(applicationContext, "please search", Toast.LENGTH_SHORT).show()
                return@OnClickListener
            }

            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "text/plain"
            intent.putExtra(Intent.EXTRA_TEXT, detail?.Title+"\n"+detail?.Poster)
            // 启动分享的活动
            startActivity(Intent.createChooser(intent, "share"))
        })
    }

    //创建菜单
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        //绑定menu菜单和xml文件
        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.feedback -> {
                startActivity(Intent(this@MainActivity,FeedBackActivity::class.java))
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun serach(title: String) {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://www.omdbapi.com") //配置URL的基地址
            .addConverterFactory(GsonConverterFactory.create()) //配置Gson转换器
            .build()
          val netService = retrofit.create(NetService::class.java) //用Retrofit对象返回一个NetService的实现

          //获取Call对象，用该对象的enqueue实现异步请求
            netService.getRepos("1849721a", title).enqueue(object: Callback<Detail> {
            override fun onResponse(call: Call<Detail>, response: Response<Detail>) {
                Log.d("输出数据",response.body().toString())
                if ("True" == response.body()?.Response) {
                    runOnUiThread {
                        detail = response.body()
                        Glide.with(this@MainActivity).load(response.body()?.Poster).into(
                            binding!!.image
                        )
                        binding!!.tvResult.text = """
                            Title:${response.body()?.Title}
                            Year:${response.body()?.Year}
                            Rated:${response.body()?.Rrated}
                            Genre:${response.body()?.Genre}
                            Director:${response.body()?.Director}
                            Writer:${response.body()?.Writer}
                            Actors:${response.body()?.Actors}
                            Language:${response.body()?.Language}
                            Country:${response.body()?.Country}
                            Awards:${response.body()?.Awards}
                            Metascore:${response.body()?.Metascore}
                            imdbRating:${response.body()?.imdbRating}
                            Plot:${response.body()?.Plot}
                            """.trimIndent() + "\n"
                    }
                    return
                }
                Toast.makeText(
                    applicationContext,
                    response.body()?.Response,
                    Toast.LENGTH_SHORT
                ).show()
            }

            override fun onFailure(call: Call<Detail>, t: Throwable) {
                Toast.makeText(applicationContext, t.message, Toast.LENGTH_SHORT).show()
            }
        })
    }
}


