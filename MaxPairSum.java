/*
    ----------MAX PAIR SUM--------------
    Suppose we have given an array and we need to find out sum  of two maximum element in each query.
    for example..
      2,5,2,6,2,8,2,9,3,0,2
                                              .Ans.
      find max sum pair between (0 to 6)--->  14 (i.e., (6+8))
                                (4 to 9)--->  17 (i.e., (8+9))
                                ....
                                so on..
                                
        
    if we do it b using brute force then it can take O(k*n) time complexity for k quries..
    We are using segment tree to solve such problem...
    
    
    code  by @AnuragSingh-01
    
*/



import java.util.*;

public class SegTree {

	 static class Node
    {
        long max;
        long smax;
    }
    
    
    public static void buildTree(int arr[], Node tree[],int start,int end,int treeNode)
    {
     
        if(start==end)
        {
            tree[treeNode].max=arr[start];
            tree[treeNode].smax=Long.MIN_VALUE;
            return;
        }
        int mid=(start+end)/2;
        buildTree(arr,tree,start,mid,2*treeNode);
        buildTree(arr,tree,mid+1,end,2*treeNode+1);
        
        tree[treeNode].max=Math.max(tree[2*treeNode].max, tree[2*treeNode+1].max);
        tree[treeNode].smax=Math.min(Math.max(tree[2*treeNode].max,tree[2*treeNode+1].smax),Math.max(tree[2*treeNode+1].max,tree[2*treeNode].smax));
        return;
    }
    
    
    public static void updateTree(int arr[], Node tree[],int start,int end,int treeNode,int idx,int value)
    {
        
        if(start==end)
        {
            arr[idx]=value;
            tree[treeNode].max=value;
            tree[treeNode].smax=Long.MIN_VALUE;
            return;
        }
        
        int mid=(start+end)/2;
        if(idx>mid)
            updateTree(arr,tree,mid+1,end,2*treeNode+1,idx,value);
        else
            updateTree(arr,tree,start,mid,2*treeNode,idx,value);
       
        tree[treeNode].max=Math.max(tree[2*treeNode].max, tree[2*treeNode+1].max);
        tree[treeNode].smax=Math.min(Math.max(tree[2*treeNode].max, tree[2*treeNode+1].smax),Math.max(tree[2*treeNode+1].max,tree[2*treeNode].smax));
        return;
    }
    
    public static Node getMaxSumOfPair(Node tree[],int start,int end,int treeNode,int left,int right)
    {
        if(end<left || right<start ||)
        {
            Node n=new Node();
            n.max=Long.MIN_VALUE;
            n.smax=Long.MIN_VALUE;
            return n; 
        }
        
        if(left<=start && end<=right)
        {
            return tree[treeNode];
        }
        
        int mid=(start+end)/2;
        Node ans1= getMaxSumOfPair(tree,start,mid,2*treeNode,left,right);
        Node ans2= getMaxSumOfPair(tree,mid+1,end,2*treeNode+1,left,right);
        
        Node temp=new Node();
        temp.max=Math.max(ans1.max,ans2.max);
        temp.smax=Math.min(Math.max(ans1.max, ans2.smax),Math.max(ans2.max,ans1.smax));
        return temp;
    }
    
	
    public static void main(String[] args) 
    {
        Scanner in=new Scanner(System.in);
        int N=in.nextInt();
        int arr[]=new int[N];
        for(int i=0;i<N;i++)
        {
            arr[i]=in.nextInt();
        }
        
        Node tree[]=new Node[3*N];
        
        for(int  i=1;i<(3*N);i++)
        {
          tree[i]=new Node();   
        }
        
        buildTree(arr,tree,0,N-1,1);
        int Q=in.nextInt();
        for(int  i=0;i<Q;i++)
        {
            char ch=in.next().charAt(0);
            int val1=in.nextInt();
            int val2=in.nextInt();
            if(ch=='Q')
            {
                Node temp=getMaxSumOfPair(tree,0,N-1,1,val1-1,val2-1);
                if(temp.smax!=Long.MIN_VALUE)
                    System.out.println(temp.max+temp.smax);
                else
                    System.out.println(temp.max);
            }
            else
                updateTree(arr,tree,0,N-1,1,val1-1,val2);
        }
	}

}
