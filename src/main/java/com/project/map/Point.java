package com.project.map;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class Point implements Comparable<Point>{
    double x;
    double y;
    double tangent = 0;
    
    public Point() {
    	 this.x = 0;
         this.y = 0;
    }

    public Point(double x, double y){
        this.x = x;
        this.y = y;
    }

    @Override
    public int compareTo(Point p){
        if(this.tangent < p.tangent)
            return -1;
        else if(this.tangent > p.tangent)
            return 1;
        return 0;
    }

	public double getX() {
		// TODO Auto-generated method stub
		return this.x;
	}
}

