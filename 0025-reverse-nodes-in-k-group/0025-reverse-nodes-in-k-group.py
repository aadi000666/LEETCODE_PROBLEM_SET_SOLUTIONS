class Solution:
    def reverseKGroup(self, head: ListNode, k: int) -> ListNode:
        def get_length(node):
            length = 0
            while node:
                length += 1
                node = node.next
            return length

        dummy = ListNode(0)
        dummy.next = head
        prev_group_end = dummy
        curr = head
        
        n = get_length(head)
        
        while n >= k:
            group_start = curr
            prev = None
            
            for _ in range(k):
                next_node = curr.next
                curr.next = prev
                prev = curr
                curr = next_node
            
            prev_group_end.next = prev
            group_start.next = curr
            
            prev_group_end = group_start
            n -= k
            
        return dummy.next