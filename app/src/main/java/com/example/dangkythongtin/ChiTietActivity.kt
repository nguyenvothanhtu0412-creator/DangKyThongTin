package com.example.dangkythongtin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.dangkythongtin.databinding.ActivityChiTietBinding

class ChiTietActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChiTietBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChiTietBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Lấy dữ liệu
        val hoTen = intent.getStringExtra("hoTen") ?: "Chưa có"
        val email = intent.getStringExtra("email") ?: "Chưa có"
        val sdt = intent.getStringExtra("sdt") ?: "Chưa có"
        val mssv = intent.getStringExtra("mssv") ?: "Chưa có"
        val lop = intent.getStringExtra("lop") ?: "Chưa có"

        // Hiển thị lên TextView (Dùng binding)
        binding.tvHoTen.text = hoTen
        binding.tvEmail.text = email
        binding.tvSDT.text = sdt
        binding.tvMSSV.text = mssv
        binding.tvLop.text = lop

        // Nút quay lại
        binding.btnQuayLai.setOnClickListener {
            finish()
        }
    }
}