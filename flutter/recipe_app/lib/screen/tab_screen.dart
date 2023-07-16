import 'package:flutter/material.dart';
import '../model/meal_model.dart';
import './favorites_screen.dart';
import './categories_screen.dart';
import '../widget/main_drawer.dart';

class TabScreen extends StatefulWidget {
  static const routeName = '/';
  final List<MealModel> favorites;

  const TabScreen(this.favorites, {Key? key}) : super(key: key);

  @override
  State<TabScreen> createState() => _TabScreenState();
}

class _TabScreenState extends State<TabScreen> {
  late List<Map<String, dynamic>> pages;
  int _selectedPage = 0;

  @override
  void initState() {
    super.initState();
    pages = <Map<String, dynamic>>[
      {
        'title': 'Categories',
        'page': const CategoryScreen(),
      },
      {
        'title': 'Favorites',
        'page': FavoritesScreen(widget.favorites),
      },
    ];
  }

  void selectPage(int id) {
    setState(() {
      _selectedPage = id;
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(pages.elementAt(_selectedPage)['title']),
      ),
      drawer: const MainDrawer(),
      body: pages.elementAt(_selectedPage)['page'],
      bottomNavigationBar: BottomNavigationBar(
        selectedItemColor: Theme.of(context).colorScheme.primary,
        unselectedItemColor: Colors.grey,
        currentIndex: _selectedPage,
        // type: BottomNavigationBarType.shifting,
        // backgroundColor: Theme.of(context).canvasColor,
        onTap: selectPage,
        items: const [
          BottomNavigationBarItem(icon: Icon(Icons.category), label: ''),
          BottomNavigationBarItem(icon: Icon(Icons.favorite), label: ''),
        ],
      ),
    );
  }
}
