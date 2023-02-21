package com.killjoy.stuntion.features.presentation.screen.video.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.Divider
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.killjoy.stuntion.R
import com.killjoy.stuntion.features.presentation.utils.components.OtherArticleItem
import com.killjoy.stuntion.ui.stuntionUI.StuntionText
import com.killjoy.stuntion.ui.theme.PrimaryBlue
import com.killjoy.stuntion.ui.theme.Type

@Composable
fun VideoDetailScreen(navController: NavController) {
    val viewModel = hiltViewModel<VideoDetailViewModel>()

    LazyColumn(modifier = Modifier.fillMaxWidth()) {

        // Image
        item {
            Box(modifier = Modifier.fillMaxWidth()) {
                AsyncImage(
                    model = R.drawable.iv_article_detail,
                    contentDescription = "Article image",
                    contentScale = ContentScale.FillWidth
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 24.dp)
                ) {
                    Image(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Arrow back icon",
                        colorFilter = ColorFilter.tint(Color.White),
                        modifier = Modifier
                            .padding(start = 16.dp)
                            .align(Alignment.TopStart)
                            .clickable { }
                    )
                    Image(
                        imageVector = Icons.Default.Share,
                        contentDescription = "Share icon",
                        colorFilter = ColorFilter.tint(Color.White),
                        modifier = Modifier
                            .padding(end = 16.dp)
                            .align(Alignment.TopEnd)
                            .clickable { }
                    )
                }
            }
        }

        // Title, reviewer, and date

        item {
            // Title
            Spacer(modifier = Modifier.height(8.dp))
            StuntionText(
                text = "Nutrition to Prevent Stunted Child Growth",
                textStyle = Type.titleLarge(),
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Divider(color = Color.LightGray, modifier = Modifier.fillMaxWidth())
            Spacer(modifier = Modifier.height(8.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                // Reviewer
                StuntionText(
                    text = "Reviewed by: dr. Fadhli Rizal Makarim",
                    textStyle = Type.bodySmall(),
                    color = PrimaryBlue,
                    modifier = Modifier.align(Alignment.CenterStart)
                )

                // Date
                Row(modifier = Modifier.align(Alignment.CenterEnd)) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_clock),
                        contentDescription = "Clock icon"
                    )
                    StuntionText(
                        text = "January 31, 2023",
                        textStyle = Type.bodySmall(),
                        color = PrimaryBlue,
                        modifier = Modifier.padding(start = 4.dp)
                    )
                }
            }
        }

        // Description
        item {
            Spacer(modifier = Modifier.height(24.dp))
            StuntionText(
                text = "Stuntion - Every parent certainly wants their child's growth and development to run optimally. In addition to providing children with various stimulations according to their age, mothers are also required to provide a variety of healthy foods so that children's growth is not hampered.\n" +
                        "\n" +
                        "There are various types of healthy food that can be given by children. However, make sure the food contains nutrients that can optimize the growth and development of children. Come on, check out the reviews on various mandatory nutrients that children need to consume during the growth and development period so that their growth is not stunted!\n" +
                        "\n" +
                        "Compulsory Nutrition to Prevent Child Growth Disorders\n" +
                        "To optimize the growth and development of children, parents need to provide a variety of diverse foods so that their nutritional needs are met properly. It's best to know the various nutrients that children must consume to prevent stunted child growth.\n" +
                        "\n" +
                        "In addition, make sure that the mother knows various types of healthy foods that can help meet the nutritional needs of children, namely:\n" +
                        "\n" +
                        "1. Proteins\n" +
                        "Protein is one of the nutrients that needs to be fulfilled every day. With proper protein intake, the body can build new cells, break down food into energy, fight infection, and help circulate oxygen in the body.\n" +
                        "\n" +
                        "Protein needs are also adjusted to the age of the child. Children aged 4-9 need about 19 grams of protein. Meanwhile, children aged 9-13 years need 34 grams of protein every day.\n" +
                        "\n" +
                        "Mothers can meet protein needs by providing a variety of foods that contain high protein. Examples include meat, fish, eggs, nuts, and dairy products.\n" +
                        "\n" +
                        "2. Carbohydrates\n" +
                        "Carbohydrates are nutrients that can be a source of energy for the body. With the fulfillment of carbohydrate needs, the body can use fat and protein to build and repair tissues.\n" +
                        "\n" +
                        "There are various types of foods that contain high carbohydrates, such as white rice, bread, cereal, pasta, and potatoes.\n" +
                        "\n" +
                        "3. Calcium\n" +
                        "Calcium is the most important nutrient for the growth of bones and teeth. Fulfillment of calcium needs to make the growth of these parts to be optimal. In addition, calcium also functions for blood clotting and nerve function, muscle health, and heart.\n" +
                        "\n" +
                        "Launching from the United States Food and Drug Administration, children are required to consume a variety of foods that contain calcium. The daily value of calcium needed for children aged 1-3 years is 700 milligrams, while children aged 4 years to adults need 1,300 milligrams.\n" +
                        "\n" +
                        "Mothers can meet children's calcium needs by providing foods, such as milk, cheese, egg yolks, broccoli, spinach, and tofu.\n" +
                        "\n" +
                        "4. Iron\n" +
                        "Iron is a mineral that functions to form red blood cells. This content is very important for the process of growth and development, immune function, reproductive function, to the wound healing process.\n" +
                        "\n" +
                        "Iron deficiency in children can trigger anemia which has the risk of causing growth and development disorders. There are various types of foods that contain iron so they are good for consumption by children. Examples include beef liver, green vegetables, seafood, eggs, and nuts.\n" +
                        "\n" +
                        "5. Vitamin C\n" +
                        "Vitamin C is a type of vitamin that needs to be fulfilled for optimal growth and development of children. By fulfilling this need, the child's immune system will function properly to prevent various health problems that can interfere with growth and development.\n" +
                        "\n" +
                        "Ages 4-8 years need 25 milligrams of vitamin C each day. While ages 9-13 years are recommended as much as 45 milligrams. Meanwhile, ages 13 and over require as much as 65-75 milligrams of vitamin C per day.\n" +
                        "\n" +
                        "Citrus fruits, such as oranges, lemons, and limes can help meet the daily needs of vitamin C. In addition, mothers can provide healthy foods, such as tomatoes, broccoli, and potatoes to meet the needs of vitamin C.\n" +
                        "\n" +
                        "Those are the various mandatory nutrients that parents need to fulfill so that children's growth can run optimally. Apart from a variety of healthy foods, mothers can meet their nutritional needs using supplements.\n" +
                        "\n" +
                        "Check children's vitamin and supplement needs and consult with a doctor using Halodoc . How to download the Halodoc application through the App Store or Google Play. The medical needs you need can be delivered anywhere!\n" +
                        "\n",
                textStyle = Type.bodyMedium(),
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }

        // Reference
        item {
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                StuntionText(
                    text = "Reference:",
                    color = PrimaryBlue,
                    textStyle = Type.titleSmall()
                )
                StuntionText(
                    text = "Parents. Accessed 2023. 11 Must-Eat Nutrients for Your Child.\n" +
                            "US Food and Drug Administration. Accessed in 2023. Key Nutrients and Your Family's Health.\n" +
                            "Grow by Web MD. Retrieved 2023. Nutrients Your Kids May Be Missing.",
                    textStyle = Type.bodySmall()
                )
            }
        }

        // Other
        item {
            Spacer(modifier = Modifier.height(24.dp))
            StuntionText(
                text = "Other Article",
                textStyle = Type.titleMedium(),
                modifier = Modifier.padding(start = 16.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
        }

        // Articles
        item {
            LazyRow(
                Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
            ) {
                items(2) {
                    OtherArticleItem(
                        title = "5 Important Nutrients for Child Growth",
                        description = "Which parent doesn't want their child to grow upWhich parent doesn't want their child to grow up",
                        category = "Nutrition"
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun ArticleDetailPreview() {
    VideoDetailScreen(navController = rememberNavController())
}