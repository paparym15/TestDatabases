package com.one.testdatabases

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.one.testdatabases.databinding.ActivityMainBinding
import com.one.testdatabases.mappers.toDomain
import com.one.testdatabases.models.Dog
import com.one.testdatabases.models.DogRealm
import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.RealmObject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityMainBinding::bind)
    private val viewModel by

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // setup Realm
        val realmName = "My Project"
        val config = RealmConfiguration.Builder()
            .name(realmName)
            .deleteRealmIfMigrationNeeded()
            .build()
        Realm.setDefaultConfiguration(config)

        // RecyclerView
        val dogAdapter = DogAdapter()
        binding.recyclerView.adapter = dogAdapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        binding.btnWrite.setOnClickListener {

            lifecycleScope.launch(Dispatchers.IO) {
                val backgroundRealmThread = Realm.getDefaultInstance()
                backgroundRealmThread.executeTransaction {
                    it.insert(DogRealm(name = "Romy"))
                }
                val dogsRealm = backgroundRealmThread.where(DogRealm::class.java).findAll()
                val dogs = dogsRealm.map {
                    it.toDomain()
                }
                withContext(Dispatchers.Main) {
                    dogAdapter.submitList(dogs)
                }
            }
        }

        binding.btnRead.setOnClickListener {
            lifecycleScope.launch(Dispatchers.IO) {
                val backgroundRealmThread = Realm.getDefaultInstance()
                val dogsRealm = backgroundRealmThread.where(DogRealm::class.java).findAll()
                val dogs = dogsRealm.map {
                    it.toDomain()
                }
                withContext(Dispatchers.Main) {
                    dogAdapter.submitList(dogs)
                }
            }
        }

        binding.btnClear.setOnClickListener {
            lifecycleScope.launch(Dispatchers.IO) {
                val backgroundRealmThread = Realm.getDefaultInstance()
                backgroundRealmThread.executeTransaction {
                    it.where(DogRealm::class.java).findAll().deleteAllFromRealm()
                }
                withContext(Dispatchers.Main) {
                    dogAdapter.submitList(emptyList())
                }
            }
        }



    }
}

