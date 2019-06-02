package it.chutien.allwaylocaldb.ui

import android.os.Build
import android.os.Bundle
import android.provider.Contacts
import android.view.View
import android.view.WindowManager
import android.widget.FrameLayout
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageButton
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.snackbar.Snackbar
import it.chutien.allwaylocaldb.R
import it.chutien.allwaylocaldb.databinding.ActivityMainBinding
import it.chutien.allwaylocaldb.realm.model.DogRealmObject
import it.chutien.allwaylocaldb.room.model.Dog
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.time.OffsetDateTime
import java.time.ZoneOffset

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
            Snackbar.make(parent_view, "Inserted ${viewModel.insertRoomDb()}", Snackbar.LENGTH_SHORT).show()
            timeQuery = System.currentTimeMillis()
            GlobalScope.launch(Dispatchers.Main) {
                showBottomSheetDialog(dogs = viewModel.getRoomDb())
            }
        }

        insert_realm.setOnClickListener {
                Snackbar.make(parent_view, "Inserted ${viewModel.insertRealmDb()}", Snackbar.LENGTH_SHORT).show()
                timeQuery = System.currentTimeMillis()
                showBottomSheetDialog(dogRealms = viewModel.getRealmDb())

        }


    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun showBottomSheetDialog(
        dogs: ArrayList<Dog>? = null,
        dogRealms: ArrayList<DogRealmObject> = ArrayList()
    ) {
        val isRealm = dogs == null
        if (mBehavior?.state == BottomSheetBehavior.STATE_EXPANDED) {
            mBehavior?.state = BottomSheetBehavior.STATE_COLLAPSED
        }
        val view = layoutInflater.inflate(R.layout.sheet_floating, null)

        view.findViewById<TextView>(R.id.query_time).text = "Query Time: ${System.currentTimeMillis() - timeQuery} ms"
        view.findViewById<TextView>(R.id.name).text = if (isRealm) "By Realm" else "By Room"
        val recyclerData = view.findViewById<RecyclerView>(R.id.recycler_data)
        recyclerData.layoutManager = LinearLayoutManager(this)

        val adapter = AdapterData(if (isRealm) convertToDogList(dogRealms) else dogs ?: ArrayList())
        adapter.setOnItemClickListener(object : AdapterData.OnItemClickListener {
            override fun onItemClick(v: View, obj: Dog, pos: Int) {
                Snackbar.make(view, "Clicked ${obj.name}", Snackbar.LENGTH_SHORT).show()
            }

        })
        recyclerData.adapter = adapter

        mBottomSheetDialog = BottomSheetDialog(this)
        mBottomSheetDialog?.setContentView(view)
        mBottomSheetDialog?.window?.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)

        mBottomSheetDialog?.show()
        mBottomSheetDialog?.setOnDismissListener {
            mBottomSheetDialog = null
            updateCount()
        }


        view.findViewById<AppCompatImageButton>(R.id.bt_close).setOnClickListener {
            mBottomSheetDialog?.dismiss()
        }

    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun convertToDogList(dogRealms: ArrayList<DogRealmObject>): ArrayList<Dog> {
        val dogs: ArrayList<Dog> = ArrayList()

        for (dogRealm in dogRealms) {
            val dog = Dog(
                dogRealm.id,
                dogRealm.name ?: "",
                dogRealm.date?.toInstant()?.atOffset(ZoneOffset.UTC) ?: OffsetDateTime.now()
            )
            dogs.add(dog)
        }

        return dogs

    }

    override fun onResume() {
        super.onResume()
        updateCount()
    }

    private fun updateCount() {
        GlobalScope.launch(Dispatchers.Main) {
            insert_room.text = "ROOM INSERT (${viewModel.getSizeRoom()})"
            insert_realm.text = "REALM INSERT (${viewModel.getSizeRealm()})"
        }
    }
}
