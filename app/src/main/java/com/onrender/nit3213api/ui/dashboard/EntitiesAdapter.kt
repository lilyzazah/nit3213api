package com.onrender.nit3213api.ui.dashboard

class EntitiesAdapter(private val onClick: (Entity) -> Unit) :
    ListAdapter<Entity, EntitiesAdapter.VH>(DIFF) {

    companion object {
        val DIFF = object : DiffUtil.ItemCallback<Entity>() {
            override fun areItemsTheSame(a: Entity, b: Entity) =
                a.property1 == b.property1 && a.property2 == b.property2
            override fun areContentsTheSame(a: Entity, b: Entity) = a == b
        }
    }

    inner class VH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tv1 = itemView.findViewById<TextView>(R.id.tvProp1)
        val tv2 = itemView.findViewById<TextView>(R.id.tvProp2)
        fun bind(entity: Entity) {
            tv1.text = entity.property1
            tv2.text = entity.property2
            itemView.setOnClickListener { onClick(entity) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        VH(LayoutInflater.from(parent.context).inflate(R.layout.item_entity, parent, false))

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(getItem(position))
    }
}
