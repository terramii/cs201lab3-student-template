import java.util.ArrayList;
import java.util.Arrays;

public class Q1Test {
     public static void main(String[] args) {

          String result, traversal1, traversal2, traversal3;

          traversal1 = "1-2";
          traversal2 = "2-1-3";
          traversal3 = "1-3-2";
          result = verify(traversal1, traversal2, traversal3);
          System.out.println("Traversal 1 : " + traversal1);
          System.out.println("Traversal 2 : " + traversal2);
          System.out.println("Traversal 3 : " + traversal3);
          System.out.println("Expected Result : Invalid traversals");
          System.out.println("  Actual Result : " + result);
          System.out.println();

          traversal1 = "1-2-3";
          traversal2 = "2-1-3";
          traversal3 = "1-3-2";
          result = verify(traversal1, traversal2, traversal3);
          System.out.println("Traversal 1 : " + traversal1);
          System.out.println("Traversal 2 : " + traversal2);
          System.out.println("Traversal 3 : " + traversal3);
          System.out.println(
                    "Expected Result : Traversal 1 - Inorder, Traversal 2 - Preorder, Traversal 3 - Postorder");
          System.out.println("  Actual Result : " + result);
          System.out.println();

          traversal1 = "1-2-3";
          traversal2 = "3-2-1";
          traversal3 = "2-3-1";
          result = verify(traversal1, traversal2, traversal3);
          System.out.println("Traversal 1 : " + traversal1);
          System.out.println("Traversal 2 : " + traversal2);
          System.out.println("Traversal 3 : " + traversal3);
          System.out.println("Expected Result : Invalid traversals");
          System.out.println("  Actual Result : " + result);
          System.out.println();

          traversal1 = "3-1-2-5-4";
          traversal2 = "2-1-4-5-3";
          traversal3 = "1-2-3-4-5";
          result = verify(traversal1, traversal2, traversal3);
          System.out.println("Traversal 1 : " + traversal1);
          System.out.println("Traversal 2 : " + traversal2);
          System.out.println("Traversal 3 : " + traversal3);
          System.out.println(
                    "Expected Result : Traversal 1 - Preorder, Traversal 2 - Postorder, Traversal 3 - Inorder");
          System.out.println("  Actual Result : " + result);
          System.out.println();

          traversal1 = "10-20-30-40-50";
          traversal2 = "20-10-30-40-50";
          traversal3 = "10-50-40-30-20";
          result = verify(traversal1, traversal2, traversal3);
          System.out.println("Traversal 1 : " + traversal1);
          System.out.println("Traversal 2 : " + traversal2);
          System.out.println("Traversal 3 : " + traversal3);
          System.out.println(
                    "Expected Result : Traversal 1 - Inorder, Traversal 2 - Preorder, Traversal 3 - Postorder");
          System.out.println("  Actual Result : " + result);
          System.out.println();
     }

     // write your codes here
     public static boolean isTraversal(String[] pre, String[] in, String[] post, int n) {
          if (pre.length != n || in.length != n || post.length != n)
               return false;
          for (int i = 1; i < n; i++) {
               if (Integer.parseInt(in[i - 1]) >= Integer.parseInt(in[i]))
                    return false;
          }
          if (n == 0) {
               return true;
          } else if (n == 1) {
               return pre[0].equals(in[0]) && in[0].equals(post[0]);
          }
          String root = pre[0];
          if (!pre[0].equals(post[n - 1]))
               return false;
          ArrayList<String> in_left = new ArrayList<>();
          ArrayList<String> in_right = new ArrayList<>();
          int tag = 0;
          int rootIndex = -1;
          for (int i = 0; i < n; i++) {
               if (!in[i].equals(root) && tag == 0) {
                    in_left.add(in[i]);
               }
               if (in[i].equals(root)) {
                    tag = 1;
                    rootIndex = i;
               }
               if (!in[i].equals(root) && tag == 1) {
                    in_right.add(in[i]);
               }
          }
          if (rootIndex == -1)
               return false;
          for (int i = 1; i <= in_left.size(); i++) {
               if (!in_left.contains(pre[i]))
                    return false;
          }
          for (int i = in_left.size() + 1; i < n; i++) {
               if (!in_right.contains(pre[i]))
                    return false;
          }
          for (int i = 0; i < in_left.size(); i++) {
               if (!in_left.contains(post[i]))
                    return false;
          }
          for (int i = in_left.size(); i < n - 1; i++) {
               if (!in_right.contains(post[i]))
                    return false;
          }
          int leftSize = rootIndex;
          String[] inLeft = Arrays.copyOfRange(in, 0, rootIndex);
          String[] inRight = Arrays.copyOfRange(in, rootIndex + 1, n);

          String[] preLeft = Arrays.copyOfRange(pre, 1, leftSize + 1);
          String[] preRight = Arrays.copyOfRange(pre, leftSize + 1, n);

          String[] postLeft = Arrays.copyOfRange(post, 0, leftSize);
          String[] postRight = Arrays.copyOfRange(post, leftSize, n - 1);

          return isTraversal(preLeft, inLeft, postLeft, inLeft.length)
                    && isTraversal(preRight, inRight, postRight, inRight.length);

     }

     public static String verify(String in1, String in2, String in3) {
          String[] input1 = in1.split("-");
          String[] input2 = in2.split("-");
          String[] input3 = in3.split("-");

          int n = input1.length;
          if (input2.length != n || input3.length != n)
               return "Invalid traversals";

          if (isTraversal(input1, input2, input3, n))
               return "Traversal 1 - Preorder, Traversal 2 - Inorder, Traversal 3 - Postorder";

          if (isTraversal(input1, input3, input2, n))
               return "Traversal 1 - Preorder, Traversal 2 - Postorder, Traversal 3 - Inorder";

          if (isTraversal(input2, input1, input3, n))
               return "Traversal 1 - Inorder, Traversal 2 - Preorder, Traversal 3 - Postorder";

          if (isTraversal(input2, input3, input1, n))
               return "Traversal 1 - Postorder, Traversal 2 - Preorder, Traversal 3 - Inorder";

          if (isTraversal(input3, input1, input2, n))
               return "Traversal 1 - Inorder, Traversal 2 - Postorder, Traversal 3 - Preorder";

          if (isTraversal(input3, input2, input1, n))
               return "Traversal 1 - Postorder, Traversal 2 - Inorder, Traversal 3 - Preorder";

          return "Invalid traversals";
     }
}