# percentrecyclerview

This function is needed to help in working with xml files in Android Studio. Helps to collect RecyclerViews on percentages by type app:layout_constraintWidth_percent or app:layout_constraintWidth_percent

# Usage

All other functions do not change and have their own properties.

<CustomRecyclerView
		android:id="@+id/recycler"
		android:layout_width="0.0dip"
		android:layout_height="0.0dip"
		android:fadeScrollbars="false"
		android:orientation="horizontal"
		app:itemHeightPercent="1.0"
		app:itemWidthPercent="0.12"
		app:layoutManager="androidx.recyclerview.widget.LinearLayoutManager"
		app:layout_constraintBottom_toTopOf="@id/recyclerBottGl"
		app:layout_constraintEnd_toStartOf="@id/lastLvlLayout"
		app:layout_constraintStart_toStartOf="@id/recyclerStartGl"
		app:layout_constraintTop_toTopOf="@id/recyclerTopGl"
		tools:listitem="@layout/battlepass_item"/>
