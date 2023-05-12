package com.example.androidfinal.filters

import android.widget.Filter
import com.example.androidfinal.adapters.GameListAdapter
import com.example.androidfinal.models.Game

class FilterGame: Filter {
        private var filterList: List<Game>
        private var adapterGameList: GameListAdapter

        constructor(filterList: List<Game>, adapterGameList: GameListAdapter) {
            this.filterList = filterList
            this.adapterGameList = adapterGameList
        }

        override fun performFiltering(constraint: CharSequence?): FilterResults {
            var constraint = constraint
            var results = FilterResults()

            if(constraint != null && constraint.isNotEmpty()) {
                constraint = constraint.toString().uppercase()

                val filterModel: ArrayList<Game> = ArrayList()

                for(i in 0 until filterList.size) {
                    if(filterList[i].name.uppercase().contains(constraint)) {
                        filterModel.add(filterList[i])
                    }
                    if(filterList[i].id.contains(constraint)) {
                        filterModel.add(filterList[i])
                    }
                }

                results.count = filterModel.size
                results.values = filterModel
            }
            else {
                results.count = filterList.size
                results.values = filterList
            }
            return results
        }

        override fun publishResults(constraint: CharSequence?, results: FilterResults) {
            adapterGameList.gameList = results.values as List<Game>

            adapterGameList.notifyDataSetChanged()
        }
}