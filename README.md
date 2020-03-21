﻿AP project (HearthStone) 
====
۱.منابع استفاده شده:
------
* [youtube_Gson tutorial](https://www.youtube.com/playlist?list=PLpUMhvC6l7AOy4UEORSutzFus98n-Es_l)
* [YouTube_Reading and writing text files](https://www.youtube.com/watch?v=BxCbxfpwC7Q)
* [Java “file exists” testing](https://alvinalexander.com/java/java-file-exists-directory-exists)
* [for get player method](https://stackoverflow.com/questions/9151619/how-to-iterate-over-a-jsonobject)
* [logFinalize method](https://stackoverflow.com/questions/20039980/java-replace-line-in-text-file)
* [logError method](https://www.programiz.com/java-programming/examples/convert-stack-trace-string)
* و...!


۲.ورژن جاوا و کتابخانه های استفاده شده:
--
* jdk-13.0.2
* gson 2.6.2
* سایر کتابخانه ها از کتابخانه استاندارد بودند 



۳.نحوه پیاده سازی فاز اول:
---
* توی ثبت نام کردن بعد از اخطار دوباره بازی شروع میشه ولی توی لاگین کردن دوباره صفحه لاگین باز میشه.نمیدونم چرا اینکارو کردم!
* طراحی لاگ جوری بوده که برای هر رخداد لاگر ساخته نشود و درهمان موقع هم بسته نشود.درواقع با ورود بازیکن فایل هندلر لاگر ساخته میشود و با خروج از بازی هم فایل هندلر بسته میشود پس اگر بطور استاندارد از بازی خارج نشویم هندلر بسته نمیشود.فکر میکنم اینکار نوشتن لاگ را سریعتر بکند اگر اینگونه نیست مشکلی نیست میتوانم در همان تابعی که لاگ مینویسد آن را باز و بسته کنم تا مطمئن باشیم هندلر همیشه کلوز است.
* چیزای کلاس هیرو پابلیکه اگه پابلیک نبودن قاطی میکردم!
* خیلی از توابع زیرکلاسهای هیرو یکی هستند من چون شک داشتم که شاید اگه اینا تو کلاس هیرو بودن، باعث میشدن که لیست های تعریف شده قاطی شن گفتم شاید بهتره که متد هارو ابسترکت کنم و جدا برای هرکدوم از هیرو ها تعریف کنم.این قسمتو باید بهینه تر و تمیز تر کنم بر فاز بعد
* special power های هیرو ها رو لحاظ نکردم.فکر کردم که برای این فاز بسه و بهتره بیشتر از این تاخیر نکنم!
* محدودیت های تعداد کارت های دست بازیکن(15)کارت راهم لحاظ نکردم به دلیلی که بالا گفتم. برای پیاده سازی آن صرفا چند شرط نسبتا ساده باید میگذاشتم

۴.دلیل استفاده از این روش طراحی و این کتابخانه ها:
----
* دلیل استفاده از جیسون که مشخصا برای خواندن و ذخیره کردن فایل ها بوده
* در این برنامه سعی شده که کارت ها در زیر کلاس های اسپل ،‌مینیون و اسلحه ساخته شوند
* هیرو های روگ، میج و وارلاک هرکدام یک زیرکلاس از کلاس هیرو هستند
* نحوه استفاده از کلاس پلیر و زیر کلاس های کلاس هیرو تا حدی شبیه سینگلتون هست البته من خیلی نمیدونم سینگلتون چیه!
* فکر میکنم اشکالی که کد من درحال حاضر دارد این است که بعضی جاها متغیر هارا مقدار دهی اولیه نکردم و دربرخی جاها هم ترتیبی که باید متد ها نوشته میشدند رعایت نشده و این ها باعث شده که کد من کار نکند. چون الان که دارم اینو مینویسم ساعت 2.53 شب هست و احتمالا باید از یک تی ای برای دیباگ کردن کد کمک بگیرم(الان تو یه مرحله از دیباگینگم هیچ ایده ای ندارم که چرا اینجور شده !) چون معلوم نیست کی بتونم با تی ای هماهنگ کنم ترجیح میدم که کد با ایراد هنگام اجرا تحویل بدهم و بیشتر از این(دو روز) تاخیر نخورم!انشاالله قبل از شروع فاز دوم پروژه حتما اشکالات این فاز درست میشوند
