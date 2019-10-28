# ClassDesign(Android)

## 2019-10-26 00:34

### I am trying to implement the Swipe-Refresh of a recyclerlayout

I uploaded the new branch - mistake，finding that Livedata can't work with adapter. I 've tried some scheme.
First, I place the "adapter.notifyDataSetChanged()" in the Observer of Livedata. After trying for some times, I found that if I just change the value of Livedata other than change the Livedata itself (change the address of Livedata), the Observer will find nothing.
And I found the article which I read serveral days ago. If I want "adapter.notifyDataSetChanged()" work, the list of recyclerlayout'data must keep unchanged, which means I can't change the Livedata itself (I can't change the address of Livedata).
It's easy to find that Observer and Livedata are contradictory. One needs the change of address, the other needs the unchange of address.
So I got the conclusion, I can't implement the function with them.

--------------------

## 2019-10-28 23:47

### I have accomplished the combination between databinding and recyclerview

I am so happy that I have finished the deployment of Google JetPack besides the combination between dataBinding and recyclerview.I will keep in mind forever that this sentence -"apply plugin: 'kotlin-kapt'" trapped me for the whole day. I spent the whole day to figure out what's the main reason of a bug - "BindingImpl 找不到符号" & "If a binding adapter provides the setter, check that the adapter is annotated correctly and that the parameter type matches".(I hate these two sentences). All in all, it is not so difficult to make databinding and recyclerview together, due to my finding a perfect wheel(haha) - a RecyclerviewAdapter specialized for databinding. Good night
