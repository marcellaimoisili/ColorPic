
1. Marcella Imoisili (mii7)

2. https://github.com/marcellaimoisili/ColorPic.git

3. The main features of my app include RGB and HSV sliders that allow users to pick unique shades/colors and retrieve cool names that represent them on the web. Ever had a specific color in mind but didn't know what it was called? As humans we're not going to be able to memorize all color hex codes. We don't refer to a specific color by its hex name lol. The problem my app tries to address is the need to find nominal descriptors for specific shades through the use of a color search.  

4. I'm most proud of my app's base functionality and the fact that I was able to really learn something tangible from this class and put it to practice! 

5. While building this app, I will be honest and say everything was a challenge, especially on a time crunch at such a hectic time. The most challenging aspect for me was wrapping my head around the concept of Networking and how data is passed around, called, and retrieved. In addition, the technical hiccups I experienced as a result of my unfamiliarity with Java & Android Studio in general didn't make the project any easier. I honestly felt like a kindergartener at a 5th grade spelling bee at times... Most of my confusion led to frustration but I always remained calm and reached out to Kevin and Lesley (after consulting good ol' Google, which confused me more at times.) In the end, I did my best given my circumstances and am proud of the effort I made (and things I learned in the process!)

6. Originally, I had planned to make my application allow users to search a specific color and retrieve Pantone (paint) products/results. Unfortunately access to Pantone's data is not free and its API isn' open source. 

I then intended for the main features of my app to include RGB and HSV sliders that allow users to pick unique  shades/colors and retrieve cool aesthetic background images that they can download or share with their friends. Unfortunately, Dribbble didn't work out and it was kinda hard to find a way to make a color search system work because not many solutions out there are doing this. 

If I had more time I would definitely incorporate a more robust system that retrieves a range of products in the specific color (or closest color) specified by the user (by possibly making my own API lol.) Maybe fabric? Shoes? Nail Polish? Who knows? :) I would also want to properly allow my implicit intent to download or send the actual image a user clicks on to their friends (instead of just a hex code. I'm interested in building upon my knowledge over the summer and attempting to create a 2.0 version of this simple Trichromatic app variation/extension. 


----- More in depth

What hasn't worked:

- I had a lot of trouble getting an OAuth callback url so I could register my app and see what kind of data I was dealing with from dribbble.

Steps I took:
Most of my steps (tried and failed) are documented on piazza under this note https://piazza.com/class/k6pumojwfk72s1?cid=76

I followed Google, Okta, and Android instructions for Authenticating to OAuth2 services but a lot of the explanations were obscure and (generally) unrelated to my project. Most of them had to do with log-in authentication which I just don't need for my project.

I stumbled across AppAuth (which looked promising) and tried to follow the steps form the GitHub repo but was completely confused about what to import or put in the dependency section of build:grade (app), from StackOverflow I tried a bunch of different import statements but nothing was working. *Not going to lie I'm extremely frustrated at this point because this seems like something that should be relatively simple and I've received minimal guidance. I feel like I am getting nowhere, wasting a lot of time.. and have other courses/finals that are equally as important. Starting to worry that I won't have a networking component fully/properly implemented.


- I also had some issues understanding and implementing how to pass around the images from dribble into my recycle view holder. However, I think I would have been able to figure it out after retrieving the API data (in the form that I needed) sadly I didn't write the API, which requires user sign on and only returns user shots. I had some placeholder code (in PicModel) where I did my best to anticipate what kinds of types/objects would be retrieved from dribble and then presented using Picasso. I would've probably explored putting this data into a singleton class that would act as a repository for my data before passing it around my app. In the end I just changed pic model to handle the color api json. 

-Picasso and my custom adapter didn't seem to work (for displaying my images) However the console wasn't throwing any errors and everything seemed to run perfectly so it was very tough to debug :/ This might also be because I didn't properly implement an Async task. I was working on this up to the deadline but have submitted what I currently have.

*Note: On the bright side After Office Hours with Aastha and Justin, I felt a lot better in terms of my understanding of Networking concepts and was able to find The ColorApi would be less of a hassle for the purposes of this final project.
The only way I could have gotten Dribbble to work in the way I wanted it to would have been by going in to the website directly through url request queries and retrieving it's html (which Justin and I were able to do) and then parsing that information for images :(

A little sad I couldn't get everything to work using Dribble (which would've been much cooler) but I put real effort in and feel good knowing I tried to do everything I could.
