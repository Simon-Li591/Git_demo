package com.Leetcode;

import com.struct.TreeNode;

class lc1038 {
    public TreeNode bstToGst(TreeNode root) {
        traverse(root);
        return root;
    }
    private int sum = 0;
    private void traverse(TreeNode root) {
        if (root == null) return;

        traverse(root.right);
        sum += root.val;
        root.val = sum;
        traverse(root.left);
    }
}
