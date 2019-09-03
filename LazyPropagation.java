package Forward;

import java.util.*;

//minimum in range with updation in range using lazy Propagation...

public class LazyPropgation
{
    public static void buildTree(int arr[],int tree [],int start,int end,int treeNode)
    {
        if(start==end)
        {
            tree[treeNode]=arr[start];
            return;
        }
        
        int mid=(start+end)/2;
        buildTree(arr,tree,start,mid,2*treeNode);
        buildTree(arr,tree,mid+1,end,2*treeNode+1);
        
        tree[treeNode]=Math.min(tree[2*treeNode],tree[2*treeNode+1]);
    }
    
    public static void updateTree(int lazyTree[],int tree[],int start,int end,int treeNode,int left,int right,int value)
    {
        if(start>end){  //agar start end se jyda h...
             return;
        }     
        
        if(lazyTree[treeNode]!=0)
        {
            tree[treeNode]+=lazyTree[treeNode];
            if(start!=end)
            {
                lazyTree[2*treeNode]+=lazyTree[treeNode];
                lazyTree[2*treeNode+1]+=lazyTree[treeNode];
            }
            lazyTree[treeNode]=0;   //here isliye taki lazytree kus node  k child ho ya na ho tab bhi lazytree 
        }                           //k us node ki value zero ho jae after adding to original tree...    
       
        // upper wala kaam toh pending work complete karne k liye h..
        //ki us node k liye pivhla koi update toh nahi h.
        
        
        
        
        //now, ab jis range m value update karni h woh dhood rahe h.....
        
        //nooverlap...
        if(end<left || right<start)
        {
            return;
        }
        
        //complete overlap...
        if(left<=start && end<=right){
               tree[treeNode]+=value;
               if(start!=end){
                   lazyTree[2*treeNode]=value;
                   lazyTree[2*treeNode+1]=value;
               }
           return;
        }
        
        //partial overlap....
        int mid=(start+end)/2;
        updateTree(lazyTree,tree,start,mid,2*treeNode,left,right,value);
        updateTree(lazyTree,tree,mid+1,end,2*treeNode+1,left,right,value);
        
        tree[treeNode] = Math.min(tree[2*treeNode],tree[2*treeNode+1]);
        
        return;
    }
    
    public static int query(int lazyTree[],int tree[],int start,int end,int treeNode,int left,int right)
    {
        
        //pehle check kar lo kya us node k liye lazy tree m kuch h..
        if(lazyTree[treeNode]!=0)
        {
            tree[treeNode]+=lazyTree[treeNode];
            if(start!=end)
            {
                lazyTree[2*treeNode]+=lazyTree[treeNode];
                lazyTree[2*treeNode+1]+=lazyTree[treeNode];
            }
            lazyTree[treeNode]=0;
        }
        
        
        //now answer ki baat karte h...... 
        if(end<left || right<start)
        {
            return Integer.MAX_VALUE;
        }
        
        if(left<=start && end<=right)
        {
            return tree[treeNode];
        }
        
        int mid=(start+end)/2;
        int l=query(lazyTree,tree,start,mid,2*treeNode,left,right);
        int r=query(lazyTree,tree,mid+1,end,2*treeNode+1,left,right);
        
        return Math.min(l,r);
    }
    
    public static void main(String args[])
    {
        int arr[]=new int[]{1,-3,2,4};
        int N=arr.length;
        int tree[]=new int[4*N];
        buildTree(arr,tree,0,N-1,1);
        
        int lazyTree[]=new int[4*N];
        for(int i=1;i<(4*N);i++)
        {
            System.out.print(tree[i]+" ");
        }
        System.out.print("\n");
        
        
        updateTree(lazyTree,tree,0,N-1,1,0,2,3);
        for(int i=1;i<(4*N);i++)
        {
            System.out.print(tree[i]+" ");
        }
        System.out.print("\n");
        updateTree(lazyTree,tree,0,N-1,1,0,3,-5);
        for(int i=1;i<(4*N);i++)
        {
            System.out.print(tree[i]+" ");
        }
        System.out.print("\n");
        updateTree(lazyTree,tree,0,N-1,1,0,0,2);
        
        for(int i=1;i<(4*N);i++)
        {
            System.out.print(tree[i]+" ");
        }
        System.out.print("\n");
        
        System.out.println(query(lazyTree,tree,0,N-1,1,0,3));
        System.out.println(query(lazyTree,tree,0,N-1,1,0,2));
        for(int i=1;i<(4*N);i++)
        {
            System.out.print(tree[i]+" ");
        }
    }
    
    
}
