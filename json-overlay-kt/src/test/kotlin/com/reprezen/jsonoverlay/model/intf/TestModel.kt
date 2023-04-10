package com.reprezen.jsonoverlay.model.intf

import com.reprezen.jsonoverlay.model.intf.Item;
import com.reprezen.jsonoverlay.model.intf.Entry;
import com.reprezen.jsonoverlay.parser.Generated;
import com.reprezen.jsonoverlay.IJsonOverlay;
import com.reprezen.jsonoverlay.model.intf.Color;
import com.reprezen.jsonoverlay.model.intf.Scalars;
import com.reprezen.jsonoverlay.IModelPart;
import kotlin.collections.List;
import kotlin.collections.Map;

interface TestModel : IJsonOverlay<TestModel>, IModelPart<TestModel, TestModel> {

	// Description
	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getDescription() : String?

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setDescription(description : String)

	// Width
	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getWidth() : Int?

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setWidth(width : Int)

	// Height
	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getHeight() : Int?

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setHeight(height : Int)

	// Entry
	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getEntries() : MutableMap<String, Entry>

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getEntries(elaborate : Boolean) : MutableMap<String, Entry>

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun hasEntries() : Boolean

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun hasEntry(name : String) : Boolean

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getEntry(name : String) : Entry?

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setEntries(entries : MutableMap<String, Entry>)

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setEntry(name : String,entry : Entry)

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun removeEntry(name : String)

	// Item
	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getItems() : List<Item>

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getItems(elaborate : Boolean) : List<Item>

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun hasItems() : Boolean

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getItem(index : Int) : Item

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setItems(items : MutableList<Item>)

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setItem(index : Int, item : Item)

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun addItem(item : Item)

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun insertItem(index : Int,item : Item)

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun removeItem(index : Int)

	// Integer
	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getIntegers() : List<Int>

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getIntegers(elaborate : Boolean) : List<Int>

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun hasIntegers() : Boolean

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getInteger(index : Int) : Int

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setIntegers(integers : MutableList<Int>)

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setInteger(index : Int, integer : Int)

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun addInteger(integer : Int)

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun insertInteger(index : Int,integer : Int)

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun removeInteger(index : Int)

	// NamedInteger
	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getNamedIntegers() : MutableMap<String, Int>

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getNamedIntegers(elaborate : Boolean) : MutableMap<String, Int>

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun hasNamedIntegers() : Boolean

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun hasNamedInteger(name : String) : Boolean

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getNamedInteger(name : String) : Int?

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setNamedIntegers(namedIntegers : MutableMap<String, Int>)

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setNamedInteger(name : String,namedInteger : Int)

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun removeNamedInteger(name : String)

	// Color
	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getColor() : Color?

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getColor(elaborate : Boolean) : Color?

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setColor(color : Color)

	// Scalar
	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getScalars() : MutableMap<String, Scalars>

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getScalars(elaborate : Boolean) : MutableMap<String, Scalars>

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun hasScalars() : Boolean

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun hasScalar(name : String) : Boolean

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getScalar(name : String) : Scalars?

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setScalars(scalars : MutableMap<String, Scalars>)

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setScalar(name : String,scalar : Scalars)

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun removeScalar(name : String)

}
