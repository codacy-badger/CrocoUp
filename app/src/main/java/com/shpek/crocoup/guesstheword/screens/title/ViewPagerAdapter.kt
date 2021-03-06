package com.shpek.crocoup.guesstheword.screens.title

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.shpek.crocoup.guesstheword.R
import kotlinx.android.synthetic.main.item_page.view.*
import timber.log.Timber

// Adapter for recycle view
class ViewPagerAdapter(private val itemClickListener: (String) -> (Unit)) : RecyclerView.Adapter<PagerVH>() {

    private var categoryList = emptyList<String>()

    private var colors = emptyList<String>()

    private var icons = emptyList<Int>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerVH =
            ItemViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_page, parent, false))

    override fun getItemCount(): Int = categoryList.size

    override fun onBindViewHolder(holder: PagerVH, position: Int) = holder.itemView.run {
        tvTitle.text = categoryList[position]
        play_card_container.setBackgroundColor(Color.parseColor(colors[position]))
        val unicode = Character.toChars(icons[position])
        iconTextView.text = String(unicode)
    }

    // set categories
    fun setCategories(categories: List<String>) {
        this.categoryList = categories
        notifyDataSetChanged()
    }

    // set icons
    fun setIcons(icons: List<Int>) {
        this.icons = icons
        notifyDataSetChanged()
    }

    // set colors
    fun setColors(colors: List<String>) {
        this.colors = colors
        notifyDataSetChanged()
    }

    private inner class ItemViewHolder(itemView: View) : PagerVH(itemView) {

        init {
            itemView.setOnClickListener {
                Timber.i("Working")
                itemClickListener(categoryList[adapterPosition])
            }
        }
    }

}

// Pager class
open class PagerVH(itemView: View) : RecyclerView.ViewHolder(itemView)