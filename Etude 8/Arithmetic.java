/**
 * This program takes a list of integer inputs and calculates if a target
 * value enteted by the user can be produced using a combination of + and *
 * operators for different methods of calculation.
 * @author Sean Kim
 */

import java.io.*;
import java.util.*;
import java.lang.*;

/** Class Arithmetic which does calculations on an array of integers to produce
 *  target value.
 */
public class Arithmetic{
  private static String lineOne, lineTwo, method, operators = "", operatorList = "";
  private static int[] numbers;
  private static int target, check = 0, left, right, store = 0;

  /** Main method that takes two lines of input and creates an integer array using
    * the first line. The second line is seperated into the method of calculation
    * to use e.g. L/N as well as the target value to produce.
    */
  public static void main(String[] args){
    Scanner sc = new Scanner(System.in);
    //Keep reading user input lines.
    while(sc.hasNextLine()){
      if(lineOne == null){
        lineOne = sc.nextLine();
        splitOne(lineOne);
      }else{
        lineTwo = sc.nextLine();
        splitTwo(lineTwo);
      }

      //If both lines are entered by the user.
      if(lineOne != null && lineTwo != null){
        //Check if the target is in range of the method.
        if(inRange(numbers, target)){
          //System.out.println("in range");
          //If the calculation is in range, use the corresponding method.
          if(method.equals("L")){
            //System.out.println("method L");
            leftToRight(numbers, target, 0, 0);
            if(check == 1){
              //operators = operators.substring((operators.length() - numbers.length + 1), operators.length());
              System.out.print(method + " " + target + " ");
              //System.out.println(operatorList);
              for(int i = 0; i < (numbers.length - 1); i++){
                System.out.print(numbers[i] + " " + operatorList.charAt(i) + " ");
              }
              System.out.println(numbers[numbers.length - 1]);
            }
            else{
              System.out.println(method + " " + target + " impossible");
            }
            reset();
          }
          else if(method.equals("N")){
            //System.out.println("method N");
            normalOrder(numbers, 0, target, 0);
            if(check == 1){
              //operators = operators.substring((operators.length() - numbers.length + 1), operators.length());
              System.out.print(method + " " + target + " ");
              //System.out.println(operatorList);
              for(int i = 0; i < (numbers.length - 1); i++){
                System.out.print(numbers[i] + " " + operatorList.charAt(i) + " ");
              }
              System.out.println(numbers[numbers.length - 1]);
            }
            else{
              System.out.println(method + " " + target + " impossible");
            }
            reset();
          }
        }else{
          System.out.println(method + " " + target + " impossible");
          reset();
        }
      }
    }
  }

  /** reset method resets all values after each calculation. */
  public static void reset(){
    lineOne = null;
    lineTwo = null;
    check = 0;
    numbers = null;
    method = "";
    operators = "";
  }

  /** left to right method calculates recursively if the target value can be produced
   *  using the left to right algorithm.
   *  @param numsArray the array of integers
   *  @param target Value to try and produce
   *  @param sum the total of each recursive calculation
   *  @param index A counter to access array of numbers and operators
   */
  public static void leftToRight(int[] numsArray, int target, int sum, int index){
    //Return out of the loop early if the solution is found.
    //System.out.println(operators);
    //System.out.println(sum);
    if(check == 1){
      return;
    }

    //If array is of size 1, check if the value matches target.
    if(numsArray.length == 1 && numsArray[0] == target){
      operatorList = operators;
      check = 1;
      return;
    }

    //If operators length is equal to 1 (e.g. no operator after last value).
    if(operators.length() >= 1 && check != 1){
      sum = 0;
      //For each operator.
      for(int i = 0; i < operators.length(); i++){
        //left = value to the left of operator.
        //right = value to the right of operator.
        left = numbers[i];
        right = numbers[i + 1];
        //if operator is + and at start of calculation.
        if(operators.charAt(i) == '+' && sum == 0){
          //Add both sides to sum.
          sum += left + right;
        }
        //if operator is + and not at start of calculation.
        else if(operators.charAt(i) == '+' && sum > 0){
          //Add right side.
          sum += right;
        }
        //if operator is * and at start of calculation.
        else if(operators.charAt(i) == '*' && sum == 0){
          //Add left and times right.
          sum += left;
          sum *= right;
        }
        //if operator is * and not at start of calculation.
        else if(operators.charAt(i) == '*' && sum > 0){
          //Times right.
          sum *= right;
        }

        //If the sum during any point gets larger than target, stop calculation.
        if(sum > target){
          return;
        }
      }
    }

    //If index reaches end of array, return back to previous operator string.
    if(index == numbers.length - 1){
      if(target == sum){
        check = 1;
        operatorList = operators;
      }
      return;
    }

    //Keep adding + as long as the total isn't larger than the target.
    operators += "+";
    leftToRight(numsArray, target, sum, index + 1);
    operators = operators.substring(0, operators.length() - 1);


    //If solution is found and we are breaking out of loop, don't bother recursing multiply.
    if(check == 1){
      return;
    }

    //Keep adding as long as the total isn't larger than the target.
    operators += "*";
    leftToRight(numsArray, target, sum, index + 1);
    operators = operators.substring(0, operators.length() - 1);
  }

  /** normalOrder method calculates recursively if the target value can be produced
   *  using the normal bedmas algorithm.
   *  @param numsArray the array of integers
   *  @param sum the total of each recursive calculation
   *  @param target Value to try and produce
   *  @param index A counter to access array of numbers and operators
   */
  public static void normalOrder(int[] numsArray, int sum, int target, int index){
    if(check == 1){
      return;
    }

    //If array is of size 1, check if the value matches target.
    if(numsArray.length == 1 && numsArray[0] == target){
      operatorList = operators;
      check = 1;
      return;
    }

    //If operators length is equal to 1 (e.g. no operator after last value).
    if(operators.length() >= 1 && check != 1){
      //Reset sum and store after each calculation.
      sum = 0;
      store = 0;
      //For each operator.
      for(int i = 0; i < operators.length(); i++){
        //System.out.println(numbers[i]);
        //System.out.println(operators.charAt(i));
        //If a calculation is stored during previous loop, set it as left, otherwise set left as number at index.
        if(store > 0){
          left = store;
          store = 0;
        }
        else{
          left = numbers[i];
        }
        //Right at index to the right of operator.
        right = numbers[i + 1];

        //System.out.println(left + " " + right);

        //if the operator is +, add left to sum.
        if(operators.charAt(i) == '+'){
          sum += left;
          //If at the end of a calculation and operator is +, also add right to sum.
          if(i == operators.length() - 1){
            sum += right;
          }
        }
        //if operator is *, store left * right. If at end of a calculation, add the stored number to sum.
        else if(operators.charAt(i) == '*'){
          store = left * right;
          if(i == operators.length() - 1){
            sum += store;
          }
        }
        //If sum gets larger than target during given operation, don't go any further and return.
        if(sum > target){
          return;
        }
      }
      //System.out.println(operators + " " + sum);
      //Return out of the loop early if the solution is found.
    }

    //If index reaches end of array - 1. Return back up recursive algorithm.
    if(index == numsArray.length - 1){
      //If the target equals sum at the end of a calculation, copy operators string to operator list. Set solution found to 1.
      if(target == sum){
        operatorList = operators;
        check = 1;
      }
      return;
    }

    //Keep adding + operators until full, then start to remove last added operator.
    operators += "+";
    normalOrder(numsArray, sum, target, index + 1);
    operators = operators.substring(0, operators.length() - 1);

    //Return out of the loop early if the solution is found.
    if(check == 1){
      return;
    }

    //Keep adding * operators until full, then start to remove last added operator.
    operators += "*";
    normalOrder(numsArray, sum, target, index + 1);
    operators = operators.substring(0, operators.length() - 1);
  }

  /** In range method that returns boolean to check if the target is within the min and max range.
    * @param numsArray array of integers
    * @param target the value trying to be produced
    * @return a boolean that returns false if target value is out of range and true if it is in range.
    */
  public static boolean inRange(int[] numsArray, int target){
    int min = numsArray[0];
    int max = numsArray[0];
    int validate = 0;

    //For values in array.
    for(int i = 1; i < numsArray.length; i++){
      //If any value in array is 1, min is N * 1, and max is N + 1.
      if(numsArray[i] == 1 || (numsArray[i - 1] == 1 && i == 1)){
        min *= numsArray[i];
        max += numsArray[i];
      }
      //Otherwise min is N + N, and max is N * N.
      else{
        min += numsArray[i];
        max *= numsArray[i];
      }
      //System.out.println(min + " min " + max + " max");
    }

    //If target out of range, return false, else, return true.
    //System.out.println(min + " min " + max + " max");
    if(target < min){
      return false;
    }
    return true;
  }

  /** splitOne method that splits the first input line using space seperators.
    * The method seperates all values and parses it as an integer into an integer array.
    * @param String the first line entered by user
    */
  public static void splitOne(String line){
    //Uses space seperators to store numbers as string array.
    String[] nums = line.split(" ");
    //Initialises numbers array using string array length as its length.
    numbers = new int[nums.length];
    //Insert string numbers into integer array by parsing string.
    for(int i = 0; i < nums.length; i++){
      numbers[i] = Integer.parseInt(nums[i]);
    }
  }

  /** splitTw0 method that splits the second input line using space seperators.
    * The method seperates the line into two variables, the first being the
    * target value and the second being the method to use.
    * @param String the second line entered by user
    */
  public static void splitTwo(String line){
    //Space seperator.
    String[] nums = line.split(" ");
    //Target is first value.
    target = Integer.parseInt(nums[0]);
    //Method is second value.
    method = nums[1];
  }
}
