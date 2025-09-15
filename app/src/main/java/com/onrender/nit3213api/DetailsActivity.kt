package com.onrender.nit3213api
@AndroidEntryPoint
class DetailsActivity : AppCompatActivity() {
    private val vm: DetailsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val passedEntity = intent.getParcelableExtra<Entity>("entity")
        passedEntity?.let { vm.setEntity(it) }

        val entity = vm.entity
        findViewById<TextView>(R.id.tvProp1).text = entity?.property1
        findViewById<TextView>(R.id.tvProp2).text = entity?.property2
        findViewById<TextView>(R.id.tvDescription).text = entity?.description
    }
}
