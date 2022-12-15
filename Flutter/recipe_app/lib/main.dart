import 'package:flutter/material.dart';
import 'dummy_data.dart';
import 'model/Filter.dart';
import 'model/meal_model.dart';
import 'screen/recipe_screen.dart';
import 'screen/meals_screen.dart';
import 'screen/categories_screen.dart';
import 'screen/settings_screen.dart';
import 'screen/tab_screen.dart';

main() => runApp(const MyApp());

class MyApp extends StatefulWidget {
  const MyApp({Key? key}) : super(key: key);

  @override
  State<MyApp> createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  Filter filter = Filter(false, false, false, false);
  List<MealModel> meals = dummyMeals;
  List<MealModel> favorites = [];

  void updateFilter(Filter filter) {
    setState(() {
      this.filter = filter;

      meals = dummyMeals.where((element) {
        if (!element.isVegetarian && filter.vegetarian) {
          return false;
        }
        if (!element.isLactoseFree && filter.lactoseFree) {
          return false;
        }
        if (!element.isGlutenFree && filter.glutenFree) {
          return false;
        }
        if (!element.isVegan && filter.vegan) {
          return false;
        }
        return true;
      }).toList();
    });
  }

  void toggleFavorite(MealModel meal) {
    favorites.contains(meal) ? favorites.remove(meal) : favorites.add(meal);
  }

  bool isFavorite(MealModel meal) {
    return favorites.any((element) => element.id == meal.id);
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: "MyApp",
      theme: ThemeData(
        colorScheme: ColorScheme.fromSwatch(primarySwatch: Colors.pink)
            .copyWith(secondary: Colors.deepOrange),
        canvasColor: const Color.fromRGBO(255, 254, 229, 1),
        textTheme: ThemeData.light().textTheme.copyWith(
              bodyMedium: const TextStyle(color: Color.fromRGBO(25, 50, 50, 1)),
              titleLarge: const TextStyle(fontSize: 22, fontFamily: 'Poppins'),
              titleMedium: const TextStyle(fontSize: 18, fontFamily: 'Poppins'),
              titleSmall: const TextStyle(fontSize: 16, fontFamily: 'Poppins'),
            ),
      ),
      // home: const CategoryScreen(),
      routes: {
        TabScreen.routeName: (context) => TabScreen(favorites),
        MealsScreen.routeName: (context) => MealsScreen(meals),
        RecipeScreen.routeName: (context) => RecipeScreen(toggleFavorite, isFavorite),
        // FavoritesScreen.routeName: (context) => const FavoritesScreen(),
        SettingsScreen.routeName: (context) =>
            SettingsScreen(filter, updateFilter),
      },
      // onGenerateRoute: (settings) {
      //   if (settings.name == '/') {
      //     ...
      //   }
      //   return MaterialPageRoute(builder: (context) => const CategoryScreen());
      // },
      onUnknownRoute: (settings) {
        return MaterialPageRoute(builder: (context) => const CategoryScreen());
      },
    );
  }
}
