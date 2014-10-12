import java.util.*;
public class KClosestPointsFromOrigin{
	private static class Point{
		Integer x;
		Integer y;
		public Point(Integer x, Integer y){
			this.x = x;
			this.y = y;
		}
		/*@Override
		public int hashCode(){
			int res = 17;
			res = 31 * res + x;
			res = 31 * res + y;
			return res;
		}
		@Override
		public boolean equals(Object other){
			if(this == other)
				return true;
			if(other == null)
				return false;
			if(!(other instanceof Point))
				return false;
			Point o = (Point)other;
			return this.x == o.x && this.y == o.y;
		}*/
		@Override
		public String toString(){
			StringBuilder sb = new StringBuilder();
			sb.append("x = "); sb.append(this.x);
			sb.append(",y = "); sb.append(this.y);
			
			return sb.toString();

		}		
	}
	private static class PointComparator implements Comparator<Point>{
		@Override
		public int compare(Point p1, Point p2){
			if(p1 != null && p2 != null)
				return (getDistanceFromOrigin(p2) - getDistanceFromOrigin(p1)) > 0 ? 1 : -1;
			else if(p1 != null)
				return -1;
			else if(p2 != null)
				return 1;
			else 
				return 0;
		}		
	}
	public static void main(String[] args){
		Point[] points = {
			new Point(1,1), new Point(2,0), new Point(3,1),
			new Point(-1,-1), new Point(-2,0), new Point(1,2),
			new Point(1,0), new Point(0,0), new Point(3,1),
		};
		
		
		printKClosestPointsFromOrigin(points, 3);
	}
	private static void printKClosestPointsFromOrigin(Point[] points, int K){
		PriorityQueue<Point> closePoints = new PriorityQueue<Point>(K,new PointComparator());
		for(int i=0; i<points.length; i++){			
			if(i < K)
				closePoints.add(points[i]);
			else{				
				if(getDistanceFromOrigin(closePoints.peek()) > getDistanceFromOrigin(points[i])){
					closePoints.poll();
					closePoints.add(points[i]);
				}
			}
			//System.out.println(closePoints.peek());
		}
		
		// for(Point p : closePoints)
		// 	System.out.println(p);
		while(closePoints.size() != 0)
			System.out.println(closePoints.poll());
		
	}
	private static double getDistanceFromOrigin(Point p){			
		return Math.sqrt(Math.pow(p.x,2) + Math.pow(p.y,2));
	}
}