package id.co.mondo.myreadwritefile

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import id.co.mondo.myreadwritefile.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonNew.setOnClickListener(this)
        binding.buttonOpen.setOnClickListener(this)
        binding.buttonSave.setOnClickListener(this)


    }
//Metode untuk membuat berkas baru
    private fun newFile() {
        binding.editTitle.setText("")
        binding.editFile.setText("")
        Toast.makeText(this,"Clearing File", Toast.LENGTH_SHORT).show()
    }

//Metode untuk membuka dan menampilkan berkas:
    private fun showList() {
        val items = fileList().filter { fileName -> (fileName != "profileInstalled") }.toTypedArray()
    val builder  = AlertDialog.Builder(this)
    builder.setTitle("Pilih file yang di inginkan")
    builder.setItems(items){
        dialog, item -> loadData(items[item].toString())
    }
    val alert = builder.create()
    alert.show()
    }

    private fun loadData(title: String) {
        val fileModel = FileHelper.readFromFile(this, title)
        binding.editTitle.setText(fileModel.fileName)
        binding.editFile.setText(fileModel.data)
        Toast.makeText(this, "Loading" + fileModel.fileName + "data", Toast.LENGTH_SHORT).show()
    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.button_new -> newFile()
                R.id.button_open -> showList()
                    R.id.button_save-> saveFile()
        }
    }

//    Metode untuk menyimpan berkas:
    private fun saveFile() {
        when{
            binding.editTitle.text.toString().isEmpty() -> Toast.makeText(this, "Title harus di isi terlebih dahulu", Toast.LENGTH_SHORT).show()
            binding.editFile.text.toString().isEmpty() -> Toast.makeText(this, "Konten harus di isi terlebih dahulu", Toast.LENGTH_SHORT).show()
            else -> {
                val title = binding.editTitle.text.toString()
                val text = binding.editFile.text.toString()
                val fileModel = FileModel()
                fileModel.fileName = title
                fileModel.data = text
                FileHelper.writeToFile(fileModel, this)
                Toast.makeText(this, "Saving " + fileModel.fileName + " file", Toast.LENGTH_SHORT).show()
            }
        }
    }
}