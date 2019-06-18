package it.chutien.allwaylocaldb.ui

import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.FrameLayout
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatImageButton
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.snackbar.Snackbar
import it.chutien.allwaylocaldb.R
import it.chutien.allwaylocaldb.databinding.ActivityMainBinding
import it.chutien.allwaylocaldb.room.model.Dog
import it.chutien.allwaylocaldb.utils.AppConstant
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity() {

    val viewModel: MainActivityViewModel by viewModel()
    lateinit var binding: ActivityMainBinding

    private var mBehavior: BottomSheetBehavior<FrameLayout>? = null
    private var mBottomSheetDialog: BottomSheetDialog? = null

    private var timeQuery = 0L

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        mBehavior = BottomSheetBehavior.from(bottom_sheet)
        binding.apply {
            setVariable(it.chutien.allwaylocaldb.BR.viewModel, viewModel)
        }


        insert_room.setOnClickListener {
            viewModel.insertRoomDb(AppConstant.KEY_VALUE)
            GlobalScope.launch(Dispatchers.Main) {
                timeQuery = System.currentTimeMillis()
                showBottomSheetDialog(dogs = viewModel.getRoomDb())
            }
        }


    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun showBottomSheetDialog(
        dogs: ArrayList<Dog>? = null
    ) {
        if (mBehavior?.state == BottomSheetBehavior.STATE_EXPANDED) {
            mBehavior?.state = BottomSheetBehavior.STATE_COLLAPSED
        }
        val view = layoutInflater.inflate(R.layout.sheet_floating, null)

        view.findViewById<TextView>(R.id.query_time).text = "Query Time: ${System.currentTimeMillis() - timeQuery} ms"
        if ((dogs?.size ?: 0 > 0)) {
            view.findViewById<TextView>(R.id.name).text = "By Room"
        }


        val recyclerData = view.findViewById<RecyclerView>(R.id.recycler_data)
        recyclerData.layoutManager = LinearLayoutManager(this)

        val adapter = AdapterData(dogs ?: ArrayList())
        adapter.setOnItemClickListener(object : AdapterData.OnItemClickListener {
            override fun onItemClick(view: View, obj: Dog, pos: Int) {
                Snackbar.make(view, "Clicked ${obj.name}", Snackbar.LENGTH_SHORT).show()
            }

        })
        recyclerData.adapter = adapter

        mBottomSheetDialog = BottomSheetDialog(this)
        mBottomSheetDialog?.setContentView(view)
        mBottomSheetDialog?.window?.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)

        // set background transparent
        (view.parent as View).setBackgroundColor(
            ContextCompat.getColor(
                applicationContext,
                android.R.color.transparent
            )
        )

        mBottomSheetDialog?.show()
        mBottomSheetDialog?.setOnDismissListener {
            mBottomSheetDialog = null
            updateCount()
        }


        view.findViewById<AppCompatImageButton>(R.id.bt_close).setOnClickListener {
            mBottomSheetDialog?.dismiss()
        }
        view.findViewById<AppCompatButton>(R.id.delete_all).setOnClickListener {
            if ((dogs?.size ?: 0 > 0)) {
                viewModel.deleteRoomDb()
            }
            mBottomSheetDialog?.dismiss()
        }

    }


    override fun onResume() {
        super.onResume()
        updateCount()
    }

    private fun updateCount() {
        GlobalScope.launch(Dispatchers.Main) {
            insert_room.text = "ROOM INSERT (${viewModel.getSizeRoom()})"
        }
    }
}
