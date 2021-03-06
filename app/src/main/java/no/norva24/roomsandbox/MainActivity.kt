package no.norva24.roomsandbox

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import androidx.room.Index.Order
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import no.norva24.roomsandbox.databinding.ActivityMainBinding
import no.norva24.roomsandbox.datalayer.localdata.entities.OrderEntity
import no.norva24.roomsandbox.datalayer.localdata.entities.OrderLineEntity
import no.norva24.roomsandbox.repositories.Repository
import kotlin.random.Random
import kotlin.random.nextInt

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        Repository.getInstance(this)

        val randomizer = Random(1000)

        CoroutineScope(Dispatchers.IO).launch {
            for(i in 1..10) {
                Repository.insertReplaceOrder(
                    OrderEntity(1, i, "Test $i")
                )
                for(j in 1 .. 10){
                    val cfDelDt = randomizer.nextInt(2)
                    val durDt = randomizer.nextInt(30)
                    Repository.insertReplaceOrderLine(
                        OrderLineEntity(1,i,j, cfDelDt = cfDelDt, dur_dt = durDt, description = "Value $cfDelDt, $durDt")
                    )
                }
            }


        }


        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

        binding.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
}