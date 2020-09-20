package com.example.orika.domain;

/**
 * Created by zhutingxuan on 2020/9/20.
 */
public class Cat {
    private String color;
    private Integer weight;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "com.example.orika.domain.Cat{" +
                "color='" + color + '\'' +
                ", weight=" + weight +
                '}';
    }
}
