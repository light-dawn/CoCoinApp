package com.shu.pocketmoneyapp.Model;

public class Record {
    public String kind;        // 类别： 收入 或 支出
    public String category;    // 收入或支出种类
    public double money;        // 数额，收入为正，支出为负
    public String description; // 对该笔账目的描述
    // 用来选择显示的条目
    public int year;
    public int month;
    public int day;

    public void setKind(String kind) {
        this.kind = kind;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getKind() {
        return this.kind;
    }

    public String getCategory() {
        return this.category;
    }

    public double getMoney() {
        return this.money;
    }

    public String getDescription() {
        return this.description;
    }

    public Record(String kind, String category, double money, String description,
                  int year, int month, int day) {
        this.kind = kind;
        this.category = category;
        this.money = money;
        this.description = description;
        this.year = year;
        this.month = month;
        this.day = day;
    }
}
