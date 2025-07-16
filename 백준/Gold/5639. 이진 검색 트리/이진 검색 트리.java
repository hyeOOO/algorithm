import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main {
    static class Node{
        int num;
        Node left;
        Node right;

        Node(){}

        Node(int num){
            this.num = num;
        }

        void insert(int n){
            if(n<this.num){
                if(this.left == null){
                    this.left = new Node(n);
                }else{
                    this.left.insert(n);
                }
            }else{
                if(this.right == null){
                    this.right = new Node(n);
                }else{
                    this.right.insert(n);
                }
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input;

        Node root = new Node(Integer.parseInt(br.readLine()));
        while (true) {
            input = br.readLine();
            if (input == null || input.equals(""))
                break;
            root.insert(Integer.parseInt(input));
        }

        postOrder(root); // 후위 순회
    }

    public static void postOrder(Node node){
        if(node == null) return;

        postOrder(node.left);
        postOrder(node.right);
        System.out.println(node.num);
    }
}
