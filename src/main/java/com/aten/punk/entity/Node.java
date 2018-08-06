package com.aten.punk.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "Node")
public class Node {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Date dateTime;
	private String coinName;
	private BigDecimal price;
	private Double changeValue;
	private Double volume;
	private Double roi;
	private Integer node;
	private Double buyGv;
	private Double sellGv;
	private Double buyCb;
	private Double sellCb;
	private Double buyCp;
	private Double sellCp;

	public Node() {}

	public Node(Long id, Date dateTime, String coinName, BigDecimal price, Double changeValue, Double volume, Double roi,
			Integer node, Double buyGv, Double sellGv, Double buyCb, Double sellCb, Double buyCp, Double sellCp) {
		super();
		this.id = id;
		this.dateTime = dateTime;
		this.coinName = coinName;
		this.price = price;
		this.changeValue = changeValue;
		this.volume = volume;
		this.roi = roi;
		this.node = node;
		this.buyGv = buyGv;
		this.sellGv = sellGv;
		this.buyCb = buyCb;
		this.sellCb = sellCb;
		this.buyCp = buyCp;
		this.sellCp = sellCp;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDateTime() {
		return dateTime;
	}

	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}

	public String getCoinName() {
		return coinName;
	}

	public void setCoinName(String coinName) {
		this.coinName = coinName;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Double getChangeValue() {
		return changeValue;
	}

	public void setChangeValue(Double changeValue) {
		this.changeValue = changeValue;
	}

	public Double getVolume() {
		return volume;
	}

	public void setVolume(Double volume) {
		this.volume = volume;
	}

	public Double getRoi() {
		return roi;
	}

	public void setRoi(Double roi) {
		this.roi = roi;
	}

	public Integer getNode() {
		return node;
	}

	public void setNode(Integer node) {
		this.node = node;
	}

	public Double getBuyGv() {
		return buyGv;
	}

	public void setBuyGv(Double buyGv) {
		this.buyGv = buyGv;
	}

	public Double getSellGv() {
		return sellGv;
	}

	public void setSellGv(Double sellGv) {
		this.sellGv = sellGv;
	}

	public Double getBuyCb() {
		return buyCb;
	}

	public void setBuyCb(Double buyCb) {
		this.buyCb = buyCb;
	}

	public Double getSellCb() {
		return sellCb;
	}

	public void setSellCb(Double sellCb) {
		this.sellCb = sellCb;
	}

	public Double getBuyCp() {
		return buyCp;
	}

	public void setBuyCp(Double buyCp) {
		this.buyCp = buyCp;
	}

	public Double getSellCp() {
		return sellCp;
	}

	public void setSellCp(Double sellCp) {
		this.sellCp = sellCp;
	}
}
