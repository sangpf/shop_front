package club.sking.core.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class RandomCodeUtil {
	
	
	public static void main(String[] args) {

		String generateNumber = generateNumber_Str(10);

		System.out.println(generateNumber);
	}
	
	public static void generate_Str(){
		String str = "多,滋,好,吃,坚,果,壳,美,味,香";
		
		int count = 0;
		
		String newStr = "";
		String[] split = str.split(",");
		
		for(int i=0; i <split.length ; i++){
			
			for(int j= 0 ; j<split.length ; j++){
				
				for(int y=0; y <split.length ; y++){
					
					newStr = split[i]+split[j]+split[y];
					
					System.out.println(newStr);
					count ++;
					
				}
				
				
			}
		}
		
		System.out.println("共有 :"+count+"个");
	}
	
	
	/**
	 * 模拟随机洗牌算法 , 获取一个随机的字母和数字的组合字符串
	 * 流程是定义一个备选集合, 该集合中存放24个英文字母和数字0-9 , 从备选集合中随机选择一个放入目标集合中 , 将选取的数据从备选集合中移除 (放到最后,并缩小选择区域)
	 * @return	随机8位不重复字母和数字
	 */
	public static String generateNumber_Str(int len){
		String generateNumber_Str = "";
		// 定义备选集合
		ArrayList<Object> defaultList = new ArrayList<Object>();
		
		// 定义一个10个长度的 int类型的数组
		int[] int_arr = new int[10];
		//填充数组
		for(int i=0;i<int_arr.length;i++){
			int_arr[i] = i;
		}
		
		// 定义一个存储所有字母的数组
		char[] char_arr =  {'a','b','c','d','e','f','g',
							'h','i','j','k','l','m','n',
							'o','p','q','r','s','t','u',
							'v','w','x','y','z',
							'A','B','C','D','E','F','G',
							'H','I','J','K','L','M','N',
							'O','P','Q','R','S','T','U',
							'V','W','X','Y','Z'};
		
		// 填充集合
		for(int num : int_arr){
			defaultList.add(num);
		}
		for(char abc : char_arr){
			defaultList.add(abc);
		}
		
		
		// 定义目标集合
		ArrayList<Object> targetList = new ArrayList<Object>();
		// 定义备选数组可以选择的长度
		int canBeUsed = 62;
		
		Random random = new Random();
		// 填充目标集合
		for(int i=0; i<len; i++){
			// 将随机选取的数据存入目标集合中
			int nextInt = random.nextInt(canBeUsed);
			//从原集合中取出一个 , 添加到新集合中
			Object object = defaultList.get(nextInt);
			targetList.add(i, object);
			// 将已用过的数字扔到备选数组最后，并减小可选区域
			swapList(nextInt, canBeUsed - 1, defaultList);
            canBeUsed--;
			
		}
		
		// 遍历list集合
		Iterator<Object> iterator = targetList.iterator();
		while(iterator.hasNext()){
			Object next = iterator.next();
			
			generateNumber_Str += next;
		}
		
		return generateNumber_Str;
		
	}
	
	    /**
	   　　* 这是典型的随机洗牌算法。
	   　　* 流程是从备选数组中选择一个放入目标数组中，将选取的数组从备选数组移除（放至最后，并缩小选择区域）
	   　　* 算法时间复杂度O（n）
	   　　* @return 随机8为不重复数组
	   　　*/
	   public static String generateNumber(int len) {
	       String no = "";
	       // 初始化备选数组
	       int[] defaultNums = new int[10];
	       // 填充数组 [0,1,2,3,......,9]
	       for (int i = 0; i < defaultNums.length; i++) {
	           defaultNums[i] = i;
	       }
	       
	       Random random = new Random();
	       //定义目标数组
	       int[] nums = new int[len];
	       // 默认数组中可以选择的部分长度
	       int canBeUsed = 10;
	       // 填充目标数组
	       for (int i = 0; i < nums.length; i++) {
	           // 将随机选取的数字存入目标数组
	           int index = random.nextInt(canBeUsed);
	           nums[i] = defaultNums[index];
	           // 将已用过的数字扔到备选数组最后，并减小可选区域
	           swap(index, canBeUsed - 1, defaultNums);
	           canBeUsed--;
	       }
	       if (nums.length > 0) {
	           for (int i = 0; i < nums.length; i++) {
	               no += nums[i];
	           }
	       }
	       return no;
	   }
	   
	   /**
	    * 交换集合中元素的方法
	    * @param i
	    * @param j
	    * @param list
	    */
	   @SuppressWarnings("unused")
	   private static void swapList(int i, int j, List<Object> list){
		   Object temp = list.get(i); //获取i的值
		   
		   Object object_J = list.get(j); //获取j的值 , 赋值给i
		   list.set(i, object_J);
		   
		   list.set(j, temp);
		   
	   }
	   
	   /**
	    * 交换方法
	    * @param i 交换位置
	    * @param j 互换的位置
	    * @param nums 数组
	    */
	   private static void swap(int i, int j, int[] nums) {
	       int temp = nums[i];
	       nums[i] = nums[j];
	       nums[j] = temp;
	   }
	   /**
	    * 获取8位数
	    * @return
	    */
	   public static String generateNumber2() {
	       String no = "";
	       int num[] = new int[8];
	       int c = 0;
	       for (int i = 0; i < 8; i++) {
	           num[i] = new Random().nextInt(10);
	           c = num[i];
	           for (int j = 0; j < i; j++) {
	               if (num[j] == c) {
	                   i--;
	                   break;
	               }
	           }
	       }
	       if (num.length > 0) {
	           for (int i = 0; i < num.length; i++) {
	               no += num[i];
	           }
	       }
	       return no;
	   }
	
	/**
	 * 获取一串随机码大小写字母加数字
	 * 
	 */
	public static String randomCode(int num) {
		StringBuffer buf = new StringBuffer("1,2,3,4,5,6,7,8,9,0");

		String[] arr = buf.toString().split(",");

		StringBuffer b = new StringBuffer();
		Random random;
		int k;
		for (int i = 0; i < num; i++) {
			random = new Random();
			k = random.nextInt(arr.length);
			b.append(arr[k]);
		}
		return b.toString();
	}
	
	/**
	 * 返回制定长度的 整数型值
	 * @param num
	 * @return
	 */
	public static Integer randomInt(int num){
		StringBuffer buf = new StringBuffer("1,2,3,4,5,6,7,8,9,0");

		String[] arr = buf.toString().split(",");

		StringBuffer sb = new StringBuffer();
		Random random;
		int k;
		for (int i = 0; i < num; i++) {
			random = new Random();
			k = random.nextInt(arr.length);
			sb.append(arr[k]);
		}
		
		return Integer.valueOf(sb.toString());
	}

}
