public class BigOrSmall{
	public static void main(String[] args){
		double min2=Math.nextUp(1);
		double max2=Math.nextUp(20);
		int smallRandNum=(int)(Math.random()*(6-2)+2);    
		System.out.printf("We will run %d times!\n",smallRandNum);
		for (int i=0; i<smallRandNum;i++){
			int var=(int)(Math.random()*(21-2)+2);
			System.out.printf("The value is: %d\n",var);
			if (var>10){
				System.out.println("Yay!");
			} else{
				System.out.println("Nay!");
			}
		}
	}
}