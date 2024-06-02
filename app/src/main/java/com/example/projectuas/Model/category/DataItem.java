package com.example.projectuas.Model.category;

import com.google.gson.annotations.SerializedName;

public class DataItem{

	@SerializedName("id_category")
	private int idCategory;

	@SerializedName("name_category")
	private String nameCategory;

	public void setIdCategory(int idCategory){
		this.idCategory = idCategory;
	}

	public int getIdCategory(){
		return idCategory;
	}

	public void setNameCategory(String nameCategory){
		this.nameCategory = nameCategory;
	}

	public String getNameCategory(){
		return nameCategory;
	}
}