Let the values denote the height of histograms: 
```java
{2,2,1,3,4,1,2}


5|        _
4|      _| |
3|_ _  | | |  _
2| | |_| | |_| |
1|_|_|_|_|_|_|_|
```


We can find the largest area of the rectangle in the Histogram by using `Stacks` with a Time Complexity of `O(n)`

Pseudocode:

```python
function(heights):
  maxarea = 0;
  pstack, hstack = Stack(), Stack()
  heights.addEnd(0) //to fool the algorithm to empty the stack in the end 

  for i in range(len(heights)):
  	lastwidth = INFINITY
    while !hstack.isEmpty() and hstack.peek() > heights[i]:
      lastwidth = pstack.peek()
      currarea = (i-pstack.pop()) * hstack.pop()
      maxarea = max(currarea, maxarea)

    if hstack.isEmpty() or hstack.peek() < heights[i]:
      hstack.push(heights[i])
      pstack.push(min(lastwidth,i))

  return maxarea
  ```
  
Largest area for the given example is:
  
```java
{2,2,1,3,4,1,2}

maxarea = 7


5|        _
4|      _| |
3|_ _  | | |  _
2|_|_|_|_|_|_|_|
1|X|X|X|X|X|X|X|
```
