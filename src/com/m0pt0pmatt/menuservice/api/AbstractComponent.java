package com.m0pt0pmatt.menuservice.api;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

import com.m0pt0pmatt.menuservice.api.actions.Action;
import com.m0pt0pmatt.menuservice.api.attributes.Attribute;
import com.m0pt0pmatt.menuservice.api.attributes.ContainerAttribute;

/**
 * A AbstractComponent is a basic implementation of a Component.
 * A AbstractComponent is designed to be extended for other ComponentTypes, but this is not necessary.
 * A AbstractComponent handles all attributes that every Component will have.
 * 
 * @author mbroomfield
 *
 */
public class AbstractComponent implements Component{
	
	private ComponentType type;
	
	private String tag;
	
	//The attributes of the component
	private Map<String, Object> attributes;
	
	private Map<Action, Set<Integer>> actionTags;
	
	/**
	 * Creates a new AbstractComponent, giving default values to all variables
	 */
	public AbstractComponent(){
		
		this.attributes = new HashMap<String, Object>();
		
		actionTags = new HashMap<Action, Set<Integer>>();
	}
	
	/**
	 * Creates a new AbstractComponent with the given attributes
	 * @param attributes
	 */
	public AbstractComponent(Map<String, Object> attributes) {
		
		//if the given attributes was null, create a new Map. Else use the provided attributes
		attributes = (attributes == null) ? new HashMap<String, Object>() : attributes;
		
		//make sure the attributes have default values
		if (!attributes.containsKey("type")) attributes.put("type", "no-type");
		if (!attributes.containsKey("tag")) attributes.put("tag", "no-tag");
		if (!attributes.containsKey("actions")) attributes.put("actions", new LinkedList<Integer>());
		if (!attributes.containsKey("lore")) attributes.put("lore", new LinkedList<String>());
		
		this.attributes = attributes;
	}
	
	//--------------------------Methods for all attributes--------------------------
	/**
	 * Returns the attributes of the Component
	 * @return
	 */
	@Override
	public Map<String, Object> getAttributes(){
		return attributes;
	}
	
	/**
	 * Sets the attributes of the Component
	 * @param attributes
	 */
	@Override
	public void setAttributes(Map<String, Object> attributes){
		this.attributes = attributes;
	}
	
	//--------------------------Methods for one attribute--------------------------
	@Override
	public Object getAttribute(Attribute attribute) {
		Object o = attributes.get(attribute.getName());
		if (o == null){
			return null;
		}
		if (o.getClass().equals(attribute.getAttributeClass())){
			return o;
		}
		return null;
	}
	
	/**
	 * Returns a given attribute of the Component
	 * @param name
	 * @return
	 */
	@Override
	public Object getAttribute(String attributeName){
		return attributes.get(attributeName);
	}
	
	@Override
	public boolean hasAttribute(Attribute attribute){
		Object o = getAttribute(attribute);
		if (o == null){
			return false;
		}
		return true;
	}
	
	/**
	 * checks if the Component has a given attribute
	 * @param name
	 * @return
	 */
	@Override
	public boolean hasAttribute(String attributeName){
		return attributes.containsKey(attributeName);
	}
	
	@Override
	public void addAttribute(Attribute attribute, Object value) {
		if (!value.getClass().equals(attribute.getAttributeClass())){
			return;
		}
		
		attributes.put(attribute.getName(), value);
	}
	
	/**
	 * Adds a attribute to the Component
	 * @param name
	 * @param value
	 */
	@Override
	public void addAttribute(String attributeName, Object value) {
		attributes.put(attributeName, value);
	}
	
	/**
	 * Returns a given attribute of the Component
	 * @param name the name of the attribute
	 * @return the attribute if it exists, otherwise null
	 */
	@Override
	public ContainerAttribute getContainerAttribute(String attributeName){
		Object attribute = getAttribute(attributeName);
		if (attribute instanceof ContainerAttribute){
			return (ContainerAttribute) attribute;
		}
		return null;
	}
	
	//--------------------------Methods for reserved attributes--------------------------
	/**
	 * Returns the type of the Component
	 * @return
	 */
	@Override
	public ComponentType getType(){
		return type;
	}
	
	@Override
	public boolean isType(ComponentType type) {
		ComponentType cType = this.getType();
		if (cType == null){
			return false;
		}
		if (cType.equals(type)){
			return true;
		}
		return false;
	}

	@Override
	public void setType(ComponentType type) {
		this.type = type;
	}
	
	/**
	 * Returns the tag of the Component
	 * @return
	 */
	@Override
	public String getTag(){
		return tag;
	}
	
	/**
	 * Sets the tag of the Component
	 * @param tag
	 */
	@Override
	public void setTag(String tag){
		this.tag = tag;
	}

	@Override
	public void addAction(Action action, Integer tag) {
		if (!actionTags.containsKey(action)){
			actionTags.put(action, new HashSet<Integer>());
		}
		
		actionTags.get(action).add(tag);
		
	}

	@Override
	public void removeAction(Action action, Integer tag) {
		if (!actionTags.containsKey(action)){
			actionTags.put(action, new HashSet<Integer>());
		}
		
		actionTags.get(action).remove(tag);
	}

	@Override
	public void removeAction(Action action) {
		actionTags.remove(action);
	}

	@Override
	public Set<Integer> getActionTags(Action action) {
		return actionTags.get(action);
	}
	
}
