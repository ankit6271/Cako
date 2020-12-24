package com.example.cako;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Database extends SQLiteOpenHelper {
    public Database(@Nullable Context context) {
        super(context, "CakoDatabase", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String SignupTable = "create table Signup(Name text,Email varchar(26),Phone Integer ,Password Varchar(26))";
        String AddressTable = "create table Address(Email varchar(26),Address varchar(26))";
        String CartTable = "create table Cart(Email varchar(26),CakeID Integer)";
        String LovedTable = "create table Loved(Email varchar(26),CakeID Integer)";
        String CakesDetail = "create table CakesDetail(CakeID Integer Primary Key AutoIncrement,Name text,Price real,Image Varchar(26),Detail varchar(36),Offer Boolean)";
        String CakoOffers = "create table CakoOffers(Code varchar(10),Offer varchar(20),OfferDetail varchar(26))";
        String PaymentOffers = "create table PaymentOffers(Code varchar(10),Offer varchar(20),OfferDetail varchar(26))";

        sqLiteDatabase.execSQL(SignupTable);
        sqLiteDatabase.execSQL(AddressTable);
        sqLiteDatabase.execSQL(CartTable);
        sqLiteDatabase.execSQL(LovedTable);
        sqLiteDatabase.execSQL(CakesDetail);
        sqLiteDatabase.execSQL(CakoOffers);
        sqLiteDatabase.execSQL(PaymentOffers);

        dataAdditionForCakes(sqLiteDatabase);
        dataAdditionForCakeoOffers(sqLiteDatabase);
    }

    private void dataAdditionForCakeoOffers(SQLiteDatabase sqLiteDatabase) {
        String code1="CAKO 50";
        String code2="CAKO 20";
        String code3="CAKO 2+1";

        String offer1="Flat 50% off ";
        String offer2="20% off ";
        String offer3="Buy 2 cakes get 1 cake free";

        String offerDetail1="Get Flat 50% off on first every payment on CAKO";
        String offerDetail2="Get Flat 20% off on payment above 1000 Rs.";
        String offerDetail3="Buy 2 cakes get 1 cake absolutely free";

        dataInsertCakoOffers(code1,offer1,offerDetail1,sqLiteDatabase);
        dataInsertCakoOffers(code2,offer2,offerDetail2,sqLiteDatabase);
        dataInsertCakoOffers(code3,offer3,offerDetail3,sqLiteDatabase);
    }

    private void dataInsertCakoOffers(String code1, String offer1, String offerDetail1, SQLiteDatabase sqLiteDatabase) {
        ContentValues values = new ContentValues();
        values.put("Code", code1);
        values.put("Offer", offer1);
        values.put("OfferDetail", offerDetail1);
        sqLiteDatabase.insert("CakoOffers", null, values);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    private void dataAdditionForCakes(SQLiteDatabase sqLiteDatabase) {


        String[] cupcakes = {"https://images.pexels.com/photos/913136/pexels-photo-913136.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500",
                "https://images.pexels.com/photos/1998635/pexels-photo-1998635.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500",
                "https://images.pexels.com/photos/3081657/pexels-photo-3081657.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500",
                "https://images.pexels.com/photos/1179002/pexels-photo-1179002.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500",
                "https://images.pexels.com/photos/853005/pexels-photo-853005.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500",
                "https://images.pexels.com/photos/1998633/pexels-photo-1998633.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500",
                "https://images.pexels.com/photos/1120464/pexels-photo-1120464.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500",
                "https://images.pexels.com/photos/818789/pexels-photo-818789.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500",
                "https://images.pexels.com/photos/718754/pexels-photo-718754.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500",
                "https://images.pexels.com/photos/4099126/pexels-photo-4099126.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500",
                "https://images.pexels.com/photos/1998636/pexels-photo-1998636.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500",
                "https://images.pexels.com/photos/4109784/pexels-photo-4109784.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500"
        };

        String[] wedding = {"https://images.pexels.com/photos/2226/food-couple-sweet-married.jpg?auto=compress&cs=tinysrgb&dpr=1&w=500",
                "https://images.pexels.com/photos/19640/pexels-photo.jpg?auto=compress&cs=tinysrgb&dpr=1&w=500",
                "https://images.pexels.com/photos/1702373/pexels-photo-1702373.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500",
                "https://images.pexels.com/photos/1038711/pexels-photo-1038711.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500",
                "https://images.pexels.com/photos/410398/pexels-photo-410398.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500",
                "https://images.pexels.com/photos/1200666/pexels-photo-1200666.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500",
                "https://images.pexels.com/photos/2510104/pexels-photo-2510104.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500",
                "https://images.pexels.com/photos/2337818/pexels-photo-2337818.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500",
                "https://images.pexels.com/photos/1829423/pexels-photo-1829423.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500",
                "https://images.pexels.com/photos/3038302/pexels-photo-3038302.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500"

        };

        String[] birthday = {"https://images.pexels.com/photos/2531546/pexels-photo-2531546.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500",
                "https://images.pexels.com/photos/4110012/pexels-photo-4110012.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500",
                "https://images.pexels.com/photos/1729797/pexels-photo-1729797.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500",
                "https://images.pexels.com/photos/369267/pexels-photo-369267.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500",
                "https://images.pexels.com/photos/1729808/pexels-photo-1729808.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500",
                "https://images.pexels.com/photos/851204/pexels-photo-851204.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500",
                "https://images.pexels.com/photos/264939/pexels-photo-264939.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500",
                "https://images.pexels.com/photos/1684039/pexels-photo-1684039.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500",
                "https://images.pexels.com/photos/5713772/pexels-photo-5713772.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500"

        };

        String[] postered = {"https://cdn.igp.com/f_auto,q_auto,t_prodm/products/p-delicious-chocolate-personalised-photo-cake-half-kg--108300-m.jpg",
                "https://cdn.igp.com/f_auto,q_auto,t_prodm/products/p-cartoon-birthday-cake-half-kg--108847-m.jpg",
                "https://cdn.igp.com/f_auto,q_auto,t_prodm/products/p-minion-birthday-cake-1-kg--108856-m.jpg",
                "https://cdn.igp.com/f_auto,q_auto,t_prodm/products/p-vanilla-personalised-photo-cake-half-kg--108301-m.jpg",
                "https://cdn.igp.com/f_auto,q_auto,t_prodm/products/p-pubg-cake-half-kg--108861-m.jpg",
                "https://cdn.igp.com/f_auto,q_auto,t_prodm/products/p-class-of-2020-graduation-cake-half-kg--114089-m.jpg",
                "https://cdn.igp.com/f_auto,q_auto,t_prodm/products/p-spiderman-cake-half-kg--108865-m.jpg",
                "https://cdn.igp.com/f_auto,q_auto,t_prodm/products/p-i-love-you-proposal-cake-half-kg--110551-m.jpg",
                "https://cdn.igp.com/f_auto,q_auto,t_prodm/products/p-be-my-valentine-cake-half-kg--110545-m.jpg",
                "https://cdn.igp.com/f_auto,q_auto,t_prodm/products/p-you-re-perfect-mom-cake-half-kg--113125-m.jpg"
        };

        String[] chocolate = {"https://images.pexels.com/photos/291528/pexels-photo-291528.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500",
                "https://images.pexels.com/photos/2680603/pexels-photo-2680603.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500",
                "https://images.pexels.com/photos/1579926/pexels-photo-1579926.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500",
                "https://images.pexels.com/photos/264892/pexels-photo-264892.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500",
                "https://images.pexels.com/photos/1120970/pexels-photo-1120970.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500",
                "https://images.pexels.com/photos/2067436/pexels-photo-2067436.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500",
                "https://images.pexels.com/photos/4109998/pexels-photo-4109998.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500",
                "https://images.pexels.com/photos/2878737/pexels-photo-2878737.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500",
                "https://images.pexels.com/photos/4109996/pexels-photo-4109996.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500",
        };

        String[] Baked = {"https://images.pexels.com/photos/3992206/pexels-photo-3992206.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500",
                "https://images.pexels.com/photos/2267872/pexels-photo-2267872.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500",
                "https://images.pexels.com/photos/776945/pexels-photo-776945.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500",
                "https://images.pexels.com/photos/4110012/pexels-photo-4110012.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500",
                "https://images.pexels.com/photos/4686836/pexels-photo-4686836.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500",
                "https://images.pexels.com/photos/1721934/pexels-photo-1721934.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500",
                "https://images.pexels.com/photos/1291712/pexels-photo-1291712.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500",
                "https://images.pexels.com/photos/1070896/pexels-photo-1070896.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500",
                "https://images.pexels.com/photos/162688/cheesecake-table-dessert-cream-162688.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500",
                "https://images.pexels.com/photos/691147/pexels-photo-691147.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500",

        };

        String[] designer = {"https://images.pexels.com/photos/433527/pexels-photo-433527.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500",
                "https://images.pexels.com/photos/1070850/pexels-photo-1070850.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500",
                "https://images.pexels.com/photos/1889651/pexels-photo-1889651.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500",
                "https://images.pexels.com/photos/159887/pexels-photo-159887.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500",
                "https://images.pexels.com/photos/1359294/pexels-photo-1359294.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500",
                "https://images.pexels.com/photos/265801/pexels-photo-265801.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500",
                "https://images.pexels.com/photos/3923555/pexels-photo-3923555.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500",
                "https://images.pexels.com/photos/302552/pexels-photo-302552.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500",
                "https://images.pexels.com/photos/3012214/pexels-photo-3012214.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500",
                "https://images.pexels.com/photos/3992396/pexels-photo-3992396.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500",
                "https://i.pinimg.com/564x/bc/ce/55/bcce552f85589012b18b82ce3da0bb84.jpg"
        };

        String[] detail = {
                "This delicious creamy cup cake is the ideal treat for the people who always love something unique. People not only love these cakes for their delicious taste of cream and cake but they look nice also. Each piece of this cake can really help you to reach the highest peak of dessert joy.",
                "Profess your love for your beloved partner in a delicious way by surprising him or her with a sumptuous wedding/anniversary cake from Cako and celebrate the years of togetherness. Order marriage anniversary cake online and get same day or midnight delivery.",
                "Ace your celebrations with birthday cakes that are just special as you are! Line up everybody and order birthday cake online for same day or midnight delivery because, with us, all the delicious wishes are granted! Send bday cake online now.",
                "Give your loved ones a pleasant surprise by sending our freshly baked cake on their anniversary. Baked using the finest ingredients, this cake is truly irresistible. And the best part is that you can customise it with a picture of your mom and dad. Order now!Avoid placing the photo and poster cake in a refrigerator as the chilled droplets can spoil the photo on the top of the cake.",
                "Award yourself with this rich chocolate cake wonderfully crammed with Cadbury Fuse and white chocolate chunks and draped lusciously with molten chocolate. This perfect work of art hides in every bite, the scrumptious flavours of heavily whipped chocolate cream and nutty bits of chocolate that is a little nutty and a lot of tasty!",
                "Baked in a tube pan like an angel food cake, it makes a very chewy sponge cake that was popular everywhere.It's still known in a slightly different form as the classic Passover sponge cake, in which the flour is replaced by matzoh cake meal and potato starch.",
                "From Barbie doll to superhero cake, from makeup-inspired to sporty deliciousness, theme cakes have become the delight of all occasions. These theme cakes are preciously crafted in various flavours like Vanilla, Butterscotch, Strawberry, etc. Make your occasions grander with the delicious range of designer cakes from Cako that are sure to infuse the optimum sweetness in every special celebration."
        };

        dataInsertCakesDetail("Pink Flavoured", 400.0F, designer[0], detail[6], false, sqLiteDatabase);
        dataInsertCakesDetail("Rose Decor", 500.0F, designer[1], detail[6], false, sqLiteDatabase);
        dataInsertCakesDetail("Strawberry Decor", 600.0F, designer[2], detail[6], false, sqLiteDatabase);
        dataInsertCakesDetail("Mango & Berries", 350.0F, designer[3], detail[6], true, sqLiteDatabase);
        dataInsertCakesDetail("Pink Flamingo", 3090.0F, designer[4], detail[6], false, sqLiteDatabase);
        dataInsertCakesDetail("Flavoured Decor", 450.0F, designer[5], detail[6], false, sqLiteDatabase);
        dataInsertCakesDetail("Decorated Flavoured Decor", 300.0F, designer[6], detail[6], true, sqLiteDatabase);
        dataInsertCakesDetail("Flower Floor Cake", 600.0F, designer[7], detail[6], false, sqLiteDatabase);
        dataInsertCakesDetail("Haloween Designed", 350.0F, designer[8], detail[6], false, sqLiteDatabase);
        dataInsertCakesDetail("Rabbit Design Cake", 320.0F, designer[9], detail[6], false, sqLiteDatabase);
        dataInsertCakesDetail("Sitting Girl Silhouette Cake", 390.0F, designer[10], detail[6], true, sqLiteDatabase);

        dataInsertCakesDetail("Baked", 356.0F, Baked[0], detail[5], false, sqLiteDatabase);
        dataInsertCakesDetail("Special Baked", 950.0F, Baked[1], detail[5], false, sqLiteDatabase);
        dataInsertCakesDetail("Baked with Strawberry topped", 450.0F, Baked[2], detail[5], false, sqLiteDatabase);
        dataInsertCakesDetail("white Iced", 260.0F, Baked[3], detail[5], false, sqLiteDatabase);
        dataInsertCakesDetail("Baked with berries", 360.0F, Baked[4], detail[5], true, sqLiteDatabase);
        dataInsertCakesDetail("Shallow White Iced", 560.0F, Baked[5], detail[5], false, sqLiteDatabase);
        dataInsertCakesDetail("Butter Cookie", 850.0F, Baked[6], detail[5], false, sqLiteDatabase);
        dataInsertCakesDetail("Lemon Pied", 500.0F, Baked[7], detail[5], false, sqLiteDatabase);
        dataInsertCakesDetail("Caramel Baked", 600.0F, Baked[8], detail[5], true, sqLiteDatabase);
        dataInsertCakesDetail("Cako Special Baked", 480.0F, Baked[9], detail[5], false, sqLiteDatabase);

        dataInsertCakesDetail("Chocolate Browned Cake", 850.0F, chocolate[0], detail[4], false, sqLiteDatabase);
        dataInsertCakesDetail("Frosting", 360.0F, chocolate[1], detail[4], true, sqLiteDatabase);
        dataInsertCakesDetail("Choco Fudge", 640.0F, chocolate[2], detail[4], false, sqLiteDatabase);
        dataInsertCakesDetail("Nutted Choco", 650.0F, chocolate[3], detail[4], false, sqLiteDatabase);
        dataInsertCakesDetail("Focus Straw Choco", 540.0F, chocolate[4], detail[4], false, sqLiteDatabase);
        dataInsertCakesDetail("Shaded Choco", 610.0F, chocolate[5], detail[4], false, sqLiteDatabase);
        dataInsertCakesDetail("Wooden Shaded", 540.0F, chocolate[6], detail[4], false, sqLiteDatabase);
        dataInsertCakesDetail("Chocoish", 460.0F, chocolate[7], detail[4], true, sqLiteDatabase);
        dataInsertCakesDetail("Choco Raspberry", 600.0F, chocolate[8], detail[4], false, sqLiteDatabase);


        dataInsertCakesDetail("Flavoured Persona", 700.0F, postered[0], detail[3], false, sqLiteDatabase);
        dataInsertCakesDetail("Cartoon", 390.0F, postered[1], detail[3], true, sqLiteDatabase);
        dataInsertCakesDetail("Minion", 350.0F, postered[2], detail[3], false, sqLiteDatabase);
        dataInsertCakesDetail("Vanilaa Persona", 360.0F, postered[3], detail[3], false, sqLiteDatabase);
        dataInsertCakesDetail("PUBG", 540.0F, postered[4], detail[3], true, sqLiteDatabase);
        dataInsertCakesDetail("Batch Of 2020", 600.0F, postered[5], detail[3], false, sqLiteDatabase);
        dataInsertCakesDetail("Spiderman", 600.0F, postered[6], detail[3], false, sqLiteDatabase);
        dataInsertCakesDetail("Proposal", 450.0F, postered[7], detail[3], false, sqLiteDatabase);
        dataInsertCakesDetail("Valentine", 650.0F, postered[8], detail[3], false, sqLiteDatabase);
        dataInsertCakesDetail("Mothers Day Special", 850.0F, postered[9], detail[3], false, sqLiteDatabase);

        dataInsertCakesDetail("Cako Special BDay Cake", 620.0F, birthday[0], detail[2], true, sqLiteDatabase);
        dataInsertCakesDetail("White Iced BDay Cake", 350.0F, birthday[1], detail[2], false, sqLiteDatabase);
        dataInsertCakesDetail("Simple Cake with Love", 650.0F, birthday[2], detail[2], false, sqLiteDatabase);
        dataInsertCakesDetail("Bday Choco Special", 450.0F, birthday[3], detail[2], false, sqLiteDatabase);
        dataInsertCakesDetail("Fruity Bday Cake", 680.0F, birthday[4], detail[2], true, sqLiteDatabase);
        dataInsertCakesDetail("Pinky Cake", 750.0F, birthday[5], detail[2], false, sqLiteDatabase);
        dataInsertCakesDetail("Mango Caramel Cake", 650.0F, birthday[6], detail[2], false, sqLiteDatabase);
        dataInsertCakesDetail("Shiny Star", 450.0F, birthday[7], detail[2], false, sqLiteDatabase);
        dataInsertCakesDetail("White + Pink Cherried", 610.0F, birthday[8], detail[2], false, sqLiteDatabase);

        dataInsertCakesDetail("Couple Figuirine", 290.0F, wedding[0], detail[1], false, sqLiteDatabase);
        dataInsertCakesDetail("White Iced Couple Cake", 380.0F, wedding[1], detail[1], false, sqLiteDatabase);
        dataInsertCakesDetail("White Iced with White Roses", 350.0F, wedding[2], detail[1], false, sqLiteDatabase);
        dataInsertCakesDetail("Made In Heaven", 390.0F, wedding[3], detail[1], false, sqLiteDatabase);
        dataInsertCakesDetail("Bride Groom special", 340.0F, wedding[4], detail[1], false, sqLiteDatabase);
        dataInsertCakesDetail("Beauty Bride Groom Cake", 393.0F, wedding[5], detail[1], false, sqLiteDatabase);
        dataInsertCakesDetail("Mr. & Mrs. Special", 1000.0F, wedding[6], detail[1], false, sqLiteDatabase);
        dataInsertCakesDetail("Dark Sunshine", 600.0F, wedding[7], detail[1], true, sqLiteDatabase);
        dataInsertCakesDetail("To be Couple Cake", 450.0F, wedding[8], detail[1], false, sqLiteDatabase);
        dataInsertCakesDetail("Cako Special Wedding Cake", 950.0F, wedding[9], detail[1], false, sqLiteDatabase);

        dataInsertCakesDetail("Red Topped", 600.0F, cupcakes[0], detail[0], false, sqLiteDatabase);
        dataInsertCakesDetail("Chocolate Cup", 650.0F, cupcakes[1], detail[0], true, sqLiteDatabase);
        dataInsertCakesDetail("Paan Flavoured", 450.0F, cupcakes[2], detail[0], false, sqLiteDatabase);
        dataInsertCakesDetail("Pink Iced", 650.0F, cupcakes[3], detail[0], true, sqLiteDatabase);
        dataInsertCakesDetail("Strawberry", 540.0F, cupcakes[4], detail[0], false, sqLiteDatabase);
        dataInsertCakesDetail("Gold Saucer", 450.0F, cupcakes[5], detail[0], false, sqLiteDatabase);
        dataInsertCakesDetail("Silver Saucer", 600.0F, cupcakes[6], detail[0], false, sqLiteDatabase);
        dataInsertCakesDetail("Choco Cup", 300.0F, cupcakes[7], detail[0], false, sqLiteDatabase);
        dataInsertCakesDetail("Brown Strawberry", 600.0F, cupcakes[8], detail[0], false, sqLiteDatabase);
        dataInsertCakesDetail("White Iced", 800.0F, cupcakes[9], detail[0], true, sqLiteDatabase);
        dataInsertCakesDetail("Strawberry Iced Cream", 500.0F, cupcakes[10], detail[0], false, sqLiteDatabase);
        dataInsertCakesDetail("Berry Cup", 540.0F, cupcakes[11], detail[0], true, sqLiteDatabase);
    }
    public void dataInsertCakesDetail(String name, Float price, String image, String detail, Boolean offer, SQLiteDatabase sqLiteDatabase) {
        ContentValues values = new ContentValues();
        values.put("Name", name);
        values.put("Price", price);
        values.put("Image", image);
        values.put("Detail", detail);
        values.put("Offer", offer);
        sqLiteDatabase.insert("CakesDetail", null, values);
    }

    public Cursor getDataInExclusiveOffersRecyclerView(){
        SQLiteDatabase sqLiteDatabase=getReadableDatabase();
        return sqLiteDatabase.rawQuery("Select cakeID,name,price,image,detail from cakesDetail where Offer=?",new String[]{"1"});
    }

    public Cursor getDataInBestSellingRecyclerView() {
        SQLiteDatabase sqLiteDatabase=getReadableDatabase();
        return sqLiteDatabase.rawQuery("Select cakeID,name,price,image,detail from cakesDetail",null);
    }

    public Cursor getDataInCakoOffersRecyclerView() {
        SQLiteDatabase sqLiteDatabase=getReadableDatabase();
        return sqLiteDatabase.query("CakoOffers",new String[]{"*"},null,null,null,null,null);
    }

    public Cursor getDataInPaymentOffersRecyclerView() {
        SQLiteDatabase sqLiteDatabase=getReadableDatabase();
        return sqLiteDatabase.rawQuery("Select * from PaymentOffers",null);
    }
}
