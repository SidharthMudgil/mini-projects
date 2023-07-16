package com.sidharth.navachar.data.remote

import com.sidharth.navachar.domain.model.Author
import com.sidharth.navachar.domain.model.Comment
import com.sidharth.navachar.domain.model.Post
import com.sidharth.navachar.domain.model.PostType
import java.util.UUID
import kotlin.random.Random

object DummyData {
    private val maleAuthor1 = Author(
        id = "1",
        name = "John Doe",
        image = "https://images.pexels.com/photos/614810/pexels-photo-614810.jpeg?cs=srgb&dl=pexels-simon-robben-614810.jpg&fm=jpg"
    )
    private val maleAuthor2 = Author(
        id = "2",
        name = "Michael Smith",
        image = "https://cdn.pixabay.com/photo/2018/10/29/21/46/human-3782189_640.jpg"
    )
    private val maleAuthor3 = Author(
        id = "3",
        name = "David Johnson",
        image = "https://images.unsplash.com/photo-1542909168-82c3e7fdca5c?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Mnx8aHVtYW4lMjBmYWNlfGVufDB8fDB8fHww&w=1000&q=80"
    )
    private val maleAuthor4 = Author(
        id = "4",
        name = "Robert Anderson",
        image = "https://static.boredpanda.com/blog/wp-content/uploads/2014/12/Top-10-photographers-for-travel-portraits14__700.jpg"
    )
    private val femaleAuthor1 = Author(
        id = "5",
        name = "Emily Johnson",
        image = "https://expertphotography.b-cdn.net/wp-content/uploads/2022/03/Portrait-Photographers-Edward-S-Curtis-Qahatika-Girl.jpg"
    )
    private val femaleAuthor2 = Author(
        id = "6",
        name = "Sophia Williams",
        image = "https://photographypro.com/wp-content/uploads/2017/08/portrait-photography-shoot-head-on-1.jpg"
    )

    private val feedbacks = listOf(
        "Great post!",
        "I completely agree.",
        "Nice photo!",
        "Well said!",
        "Interesting discussion.",
        "I have a different perspective.",
        "Thanks for sharing.",
        "I love Sidharth Mudgil",
        "This is inspiring.",
        "I love this post!",
        "Can't wait to see more!",
    )

    private val authors = listOf(
        maleAuthor1, maleAuthor2, maleAuthor3, maleAuthor4, femaleAuthor1, femaleAuthor2
    )

    private val captions = listOf(
        "Discover our latest product and elevate your lifestyle!",
        "Limited-time offer: Don't miss out on our exclusive deals!",
        "Upgrade your wardrobe with our trendy fashion collection.",
        "Transform your home with our high-quality home decor items.",
        "Experience luxury like never before with our premium services.",
        "What are your favorite travel destinations?",
        "How do you stay motivated and productive?",
        "What's your go-to recipe for a delicious meal?",
        "Share your best tips for successful networking.",
        "What books have had a significant impact on your life?",
    )

    private val images = listOf(
        "https://c4.wallpaperflare.com/wallpaper/280/258/513/cat-dark-black-profile-wallpaper-preview.jpg",
        "https://png.pngtree.com/background/20230519/original/pngtree-long-haired-cat-laying-down-in-front-of-a-black-background-picture-image_2658930.jpg",
        "https://img.freepik.com/premium-photo/close-up-portrait-brown-burmese-cat-with-chocolate-fur-color-yellow-eyes-black_74947-2267.jpg?w=740",
        "https://img.freepik.com/premium-photo/wide-banner-with-scottish-fold-cat-portrait-studio-photo-black-isolated-backgrounds-copy-space_526934-2484.jpg?w=900",
        "https://media.istockphoto.com/id/1277118214/photo/black-and-white-studio-shot-of-a-black-cat-relaxing-and-looking-at-camera.jpg?s=612x612&w=0&k=20&c=mV0BPkLdO9KNVMjU56iKUZE6rcr9ciMib4x9lf5uWJI=",
        "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQTDNrH0DKQN1J7S781huR3LnxR-1p65yE7uw&usqp=CAU",
        "https://media.istockphoto.com/id/1277118214/photo/black-and-white-studio-shot-of-a-black-cat-relaxing-and-looking-at-camera.jpg?s=612x612&w=0&k=20&c=mV0BPkLdO9KNVMjU56iKUZE6rcr9ciMib4x9lf5uWJI=",
        "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRgmYkQwJ5I1VFCd-pM67lMA4MBo_1bFUQp2A&usqp=CAU",
        "https://w0.peakpx.com/wallpaper/533/709/HD-wallpaper-owl-eyes-owl-eyes-dark-staring-bird-black.jpg",
        "https://c4.wallpaperflare.com/wallpaper/213/34/409/crow-bird-black-drawing-hd-wallpaper-preview.jpg",
        "https://images.unsplash.com/photo-1506220926022-cc5c12acdb35?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8M3x8ZGFyayUyMGJpcmR8ZW58MHx8MHx8fDA%3D&w=1000&q=80",
        "https://p4.wallpaperbetter.com/wallpaper/199/100/59/fantasy-art-andrew-domachowski-horse-dark-hd-wallpaper-preview.jpg",
        "https://img.freepik.com/premium-photo/black-white-portrait-gorgeous-male-lion-against-black-background-dark-moody-animal-photo_106630-1218.jpg",
        "https://i.pinimg.com/originals/72/df/78/72df78eb7d62012364460f6b8669ab63.jpg",
        "https://media.istockphoto.com/id/670210098/photo/blue-peacock.jpg?s=612x612&w=0&k=20&c=2POWdZMMuSOM7-6phccq1wbMWrZle0pMUBUVJDcntOg=",
        "https://images.unsplash.com/photo-1567172180864-3a117bc0d02b?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Mnx8YmxhY2slMjBlYWdsZXxlbnwwfHwwfHx8MA%3D%3D&w=1000&q=80",
        "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTPaVkUot0I3qDR9gNr6MMh3fgQg3FElC81JQ&usqp=CAU",
        "https://img.freepik.com/premium-photo/giraffe-dark-background-picture-black-white_410516-42671.jpg",
    )

    val posts: List<Post> get() = generatePosts()

    private fun generatePosts(): List<Post> {
        val likes = Random.nextInt(100)
        val posts = mutableListOf<Post>()

        repeat(10) {
            val totalImages = (0..5).random()
            val totalComments = Random.nextInt(feedbacks.size)
            val comments = mutableListOf<Comment>()

            repeat(totalComments) {
                comments.add(
                    Comment(
                        id = UUID.randomUUID().toString(),
                        author = authors.random(),
                        comment = feedbacks.random(),
                        likes = Random.nextInt(likes),
                    )
                )
            }

            val post = Post(
                id = UUID.randomUUID().toString(),
                author = authors.random(),
                type = PostType.values().random(),
                caption = captions.random(),
                images = images.shuffled().take(totalImages),
                likes = likes,
                totalComments = totalComments,
                comments = comments
            )

            posts.add(post)
        }

        return posts
    }
}