package com.example.dangkythongtin

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Patterns
import androidx.appcompat.app.AppCompatActivity
import com.example.dangkythongtin.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    // Khai báo ViewBinding
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Khởi tạo ViewBinding thay vì setContentView
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Hàm kiểm tra dữ liệu
        fun validate(): Boolean {
            // Reset lỗi
            binding.tilHoTen.error = null
            binding.tilEmail.error = null
            binding.tilSDT.error = null
            binding.tilMSSV.error = null
            binding.tilLop.error = null

            // Lấy dữ liệu (dùng binding thay cho findViewById)
            val hoTen = binding.edtHoTen.text.toString().trim()
            val email = binding.edtEmail.text.toString().trim()
            val sdt = binding.edtSDT.text.toString().trim()
            val mssv = binding.edtMSSV.text.toString().trim()
            val lop = binding.edtLop.text.toString().trim()

            var isValid = true

            // Validate Họ Tên
            if (hoTen.isEmpty()) {
                binding.tilHoTen.error = "Vui lòng nhập họ tên"
                isValid = false
            } else if (hoTen.length < 3) {
                binding.tilHoTen.error = "Họ tên quá ngắn"
                isValid = false
            }

            // Validate Email
            if (email.isEmpty()) {
                binding.tilEmail.error = "Vui lòng nhập email"
                isValid = false
            } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                binding.tilEmail.error = "Email không hợp lệ (VD: a@gmail.com)"
                isValid = false
            }

            // Validate SĐT
            if (sdt.isEmpty()) {
                binding.tilSDT.error = "Vui lòng nhập SĐT"
                isValid = false
            } else if (!sdt.matches(Regex("^0\\d{9}$"))) {
                binding.tilSDT.error = "SĐT phải có 10 số và bắt đầu bằng 0"
                isValid = false
            }

            // Validate MSSV
            if (mssv.isEmpty()) {
                binding.tilMSSV.error = "Vui lòng nhập MSSV"
                isValid = false
            } else if (!mssv.matches(Regex("^\\d{8,13}$"))) {
                binding.tilMSSV.error = "MSSV phải là 8-13 chữ số"
                isValid = false
            }

            // Validate Lớp
            if (lop.isEmpty()) {
                binding.tilLop.error = "Vui lòng nhập lớp"
                isValid = false
            }

            return isValid
        }

        // Hàm phụ để chuyển Intent (Tránh lặp code)
        fun chuyenTrang() {
            val intent = Intent(this, ChiTietActivity::class.java).apply {
                putExtra("hoTen", binding.edtHoTen.text.toString().trim())
                putExtra("email", binding.edtEmail.text.toString().trim())
                putExtra("sdt", binding.edtSDT.text.toString().trim())
                putExtra("mssv", binding.edtMSSV.text.toString().trim())
                putExtra("lop", binding.edtLop.text.toString().trim())
            }
            startActivity(intent)
        }
        // Sự kiện nút ĐĂNG KÝ
        binding.btnDangKy.setOnClickListener { view ->
            if (validate()) {
                // Pass hết -> Hiện Snackbar
                Snackbar.make(view, "Đăng ký thành công!", Snackbar.LENGTH_LONG)
                    .setBackgroundTint(Color.parseColor("#4CAF50"))
                    .setActionTextColor(Color.WHITE)
                    .setAction("Xem chi tiết") {
                        chuyenTrang() // Bấm nút trên Snackbar -> chuyển trang
                    }.show()
            }
        }

        // Sự kiện nút XEM CHI TIẾT
        binding.btnQuaChiTiet.setOnClickListener {
            chuyenTrang()
        }
    }
}