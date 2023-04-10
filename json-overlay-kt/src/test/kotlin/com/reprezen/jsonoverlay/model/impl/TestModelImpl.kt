package com.reprezen.jsonoverlay.model.impl;

import com.reprezen.jsonoverlay.model.intf.*;
import com.reprezen.jsonoverlay.MapOverlay;
import java.util.stream.Collectors;
import com.reprezen.jsonoverlay.StringOverlay;
import com.reprezen.jsonoverlay.parser.Generated;
import com.reprezen.jsonoverlay.IJsonOverlay;
import com.reprezen.jsonoverlay.ListOverlay;
import com.reprezen.jsonoverlay.OverlayFactory;
import com.reprezen.jsonoverlay.Builder;
import com.reprezen.jsonoverlay.model.impl.EntryImpl;
import com.reprezen.jsonoverlay.PropertiesOverlay;
import com.reprezen.jsonoverlay.ReferenceManager;
import com.reprezen.jsonoverlay.IntegerOverlay;
import com.reprezen.jsonoverlay.model.impl.ScalarsImpl;
import com.reprezen.jsonoverlay.model.impl.ItemImpl;
import kotlin.collections.List;
import com.reprezen.jsonoverlay.JsonOverlay;
import com.reprezen.jsonoverlay.model.impl.ColorImpl;
import kotlinx.serialization.json.JsonElement
import kotlin.collections.Map;

class TestModelImpl : PropertiesOverlay<TestModel> ,TestModel {

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	constructor(json : JsonElement, parent : JsonOverlay<*>?, refMgr : ReferenceManager) : super(json, parent, Companion.factory, refMgr)

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	constructor(testModel : TestModel?, parent : JsonOverlay<*>?, refMgr : ReferenceManager) : super(testModel, parent, Companion.factory, refMgr)

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	// Description
	override fun getDescription() : String? {
		return _get("description", String::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setDescription(description : String) {
		_setScalar("description", description, String::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	// Width
	override fun getWidth() : Int? {
		return _get("width", Int::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setWidth(width : Int) {
		_setScalar("width", width, Int::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	// Height
	override fun getHeight() : Int? {
		return _get("height", Int::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setHeight(height : Int) {
		_setScalar("height", height, Int::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	// Entry
	override fun getEntries() : MutableMap<String, Entry> {
		return _getMap("entries", Entry::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun getEntries(elaborate : Boolean) : MutableMap<String, Entry> {
		return _getMap("entries", elaborate, Entry::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun hasEntries() : Boolean {
		return _isPresent("entries")
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun hasEntry(name : String) : Boolean {
		return _getMap("entries", Entry::class.java).containsKey(name)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun getEntry(name : String) : Entry? {
		return _get("entries", name, Entry::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setEntries(entries : MutableMap<String, Entry>) {
		_setMap("entries", entries, Entry::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setEntry(name : String, entry : Entry) {
		_set("entries", name, entry, Entry::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun removeEntry(name : String) {
		_remove("entries", name, Entry::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	// Item
	override fun getItems() : List<Item> {
		return _getList("items", Item::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun getItems(elaborate : Boolean) : List<Item> {
		return _getList("items", elaborate, Item::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun hasItems() : Boolean {
		return _isPresent("items")
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun getItem(index : Int) : Item {
		return _get("items", index, Item::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setItems(items : MutableList<Item>) {
		_setList("items", items, Item::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setItem(index : Int, item : Item) {
		_set("items", index, item, Item::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun addItem(item : Item) {
		_add("items", item, Item::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun insertItem(index : Int, item : Item) {
		_insert("items", index, item, Item::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun removeItem(index : Int) {
		_remove("items", index, Item::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	// Integer
	override fun getIntegers() : List<Int> {
		return _getList("integers", Int::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun getIntegers(elaborate : Boolean) : List<Int> {
		return _getList("integers", elaborate, Int::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun hasIntegers() : Boolean {
		return _isPresent("integers")
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun getInteger(index : Int) : Int {
		return _get("integers", index, Int::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setIntegers(integers : MutableList<Int>) {
		_setList("integers", integers, Int::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setInteger(index : Int, integer : Int) {
		_set("integers", index, integer, Int::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun addInteger(integer : Int) {
		_add("integers", integer, Int::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun insertInteger(index : Int, integer : Int) {
		_insert("integers", index, integer, Int::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun removeInteger(index : Int) {
		_remove("integers", index, Int::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	// NamedInteger
	override fun getNamedIntegers() : MutableMap<String, Int> {
		return _getMap("namedIntegers", Int::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun getNamedIntegers(elaborate : Boolean) : MutableMap<String, Int> {
		return _getMap("namedIntegers", elaborate, Int::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun hasNamedIntegers() : Boolean {
		return _isPresent("namedIntegers")
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun hasNamedInteger(name : String) : Boolean {
		return _getMap("namedIntegers", Int::class.java).containsKey(name)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun getNamedInteger(name : String) : Int? {
		return _get("namedIntegers", name, Int::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setNamedIntegers(namedIntegers : MutableMap<String, Int>) {
		_setMap("namedIntegers", namedIntegers, Int::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setNamedInteger(name : String, namedInteger : Int) {
		_set("namedIntegers", name, namedInteger, Int::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun removeNamedInteger(name : String) {
		_remove("namedIntegers", name, Int::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	// Color
	override fun getColor() : Color? {
		return _get("color", Color::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun getColor(elaborate : Boolean) : Color? {
		return _get("color", elaborate, Color::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setColor(color : Color) {
		_setScalar("color", color, Color::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	// Scalar
	override fun getScalars() : MutableMap<String, Scalars> {
		return _getMap("scalars", Scalars::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun getScalars(elaborate : Boolean) : MutableMap<String, Scalars> {
		return _getMap("scalars", elaborate, Scalars::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun hasScalars() : Boolean {
		return _isPresent("scalars")
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun hasScalar(name : String) : Boolean {
		return _getMap("scalars", Scalars::class.java).containsKey(name)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun getScalar(name : String) : Scalars? {
		return _get("scalars", name, Scalars::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setScalars(scalars : MutableMap<String, Scalars>) {
		_setMap("scalars", scalars, Scalars::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setScalar(name : String, scalar : Scalars) {
		_set("scalars", name, scalar, Scalars::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun removeScalar(name : String) {
		_remove("scalars", name, Scalars::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun _elaborateJson() {
		super._elaborateJson()
		_createScalar("description", "description", StringOverlay.factory)
		_createScalar("width", "width", IntegerOverlay.factory)
		_createScalar("height", "height", IntegerOverlay.factory)
		_createMap("entries", "entries", EntryImpl.factory, null)
		_createList("items", "items", ItemImpl.factory)
		_createList("integers", "integers", IntegerOverlay.factory)
		_createMap("namedIntegers", "namedIntegers", IntegerOverlay.factory, null)
		_createScalar("color", "color", ColorImpl.factory)
		_createMap("scalars", "scalars", ScalarsImpl.factory, null)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun _getModelType() : Class<*> {
		return TestModel::class.java
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun _getFactory() : OverlayFactory<TestModel> {
		return Companion.factory
	}

	companion object {
		const val F_description : String = "description"

		const val F_width : String = "width"

		const val F_height : String = "height"

		const val F_entries : String = "entries"

		const val F_items : String = "items"

		const val F_integers : String = "integers"

		const val F_namedIntegers : String = "namedIntegers"

		const val F_color : String = "color"

		const val F_scalars : String = "scalars"

		val factory = object : OverlayFactory<TestModel>() {
		
			override fun getOverlayClass() : Class<out JsonOverlay<in TestModel>> {
				return TestModelImpl::class.java
			}
		
			override fun _create(testModel : TestModel?, parent : JsonOverlay<*>?, refMgr : ReferenceManager) : JsonOverlay<TestModel> {
				return TestModelImpl(testModel, parent, refMgr)
			}
		
			override fun _create(json : JsonElement, parent : JsonOverlay<*>?, refMgr : ReferenceManager) : JsonOverlay<TestModel> {
				return TestModelImpl(json, parent, refMgr)
			}
		
			override val isExtendedType : Boolean get() = false
		};

		private fun getSubtypeOf(testModel : TestModel) : Class<out TestModel> {
			return TestModel::class.java
		}

		private fun getSubtypeOf(json : JsonElement) : Class<out TestModel> {
			return TestModel::class.java
		}
		

		fun builder(modelMember : JsonOverlay<*>) : Builder<TestModel> {
			return Builder<TestModel>(factory, modelMember)
		}

		fun create(modelMember : JsonOverlay<*>) : TestModel {
			return builder(modelMember).build() as TestModel
		}
	}

}
