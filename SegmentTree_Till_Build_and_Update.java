/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package segment_tree;

public class SegmentTree_Till_Build_and_Update
{
    public static void buildTree(int arr[],int tree[],int start, int end,int treeNode)
    {
        if(start==end)
        {
            tree[treeNode]=arr[start];
            return;
        }
        int mid=(start+end)/2;
        buildTree(arr,tree,start,mid,2*treeNode);
        buildTree(arr,tree,mid+1,end,2*treeNode+1);
        tree[treeNode]=tree[2*treeNode]+tree[2*treeNode+1];
    }
    
    public static void updateTree(int arr[],int tree[],int start,int end,int treeNode,int idx,int value)
    {
        if(start==end)
        {
            arr[idx]=value;
            tree[treeNode]=value;
            return;
        }
        int mid=(start+end)/2;
        
        if(idx>mid)
            updateTree(arr,tree,mid+1,end,2*treeNode+1,idx,value);
        else
            updateTree(arr,tree,start,mid,2*treeNode,idx,value);
        
        tree[treeNode]=tree[2*treeNode]+tree[2*treeNode+1];
    }
    
    public static int query(int tree[],int start,int end,int treeNode,int left,int right)
    {
        //completely outside
        if(end<left || start>right)
        {
            return 0;
        }
        
        //completelty inside...
        if(left<=start && end<=right)
        {
            return tree[treeNode];
        }
        
        //partilly outsie and inside..
        int mid=(start+end)/2;
        int ans1=query(tree,start,mid,2*treeNode,left,right);
        int ans2=query(tree,mid+1,end,2*treeNode+1,left,right);
        
        return (ans1+ans2);
        
        
    }
    
    public static void main(String args[])
    {
        int arr[]=new int[]{1,2,3,4,5,6,7,8,9,10};
        int N=arr.length;
        int tree[]=new int[4*N];
        
        buildTree(arr,tree,0,N-1,1);
        for(int i=1;i<(4*N);i++)
          System.out.print(tree[i]+" ");
        
        System.out.println();
        
       //s updateTree(arr,tree,0,N-1,1,2,10);
        for(int i=1;i<(4*N);i++)
          System.out.print(tree[i]+" ");
        
        System.out.println("Query  :"+query(tree,0,N-1,1,0,4)); 
    }
}