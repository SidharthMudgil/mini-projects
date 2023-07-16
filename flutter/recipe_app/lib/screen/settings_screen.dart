import 'package:flutter/material.dart';
import 'package:recipe_app/model/Filter.dart';
import 'package:recipe_app/widget/main_drawer.dart';

class SettingsScreen extends StatefulWidget {
  static const routeName = '/settings_screen';
  final Filter filter;
  final Function function;

  const SettingsScreen(this.filter, this.function, {Key? key})
      : super(key: key);

  @override
  State<SettingsScreen> createState() => _SettingsScreenState();
}

class _SettingsScreenState extends State<SettingsScreen> {
  var vegetarian = false;
  var lactoseFree = false;
  var glutenFree = false;
  var vegan = false;

  @override
  void initState() {
    super.initState();
    vegetarian = widget.filter.vegetarian;
    lactoseFree = widget.filter.lactoseFree;
    glutenFree = widget.filter.glutenFree;
    vegan = widget.filter.vegan;
  }

  Widget buildSwitchListTile(
      String title, String subtitle, bool currentValue, Function update) {
    return SwitchListTile(
      title: Text(title),
      subtitle: Text(subtitle),
      activeColor: Theme.of(context).colorScheme.primary,
      value: currentValue,
      onChanged: (value) => update(value),
    );
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('Settings'),
      ),
      drawer: const MainDrawer(),
      body: Column(
        children: [
          Container(
            alignment: Alignment.topLeft,
            padding: const EdgeInsets.all(20),
            child: Text(
              'Filter your meals',
              style: Theme.of(context)
                  .textTheme
                  .titleMedium
                  ?.copyWith(fontWeight: FontWeight.w700),
            ),
          ),
          buildSwitchListTile(
            'Vegetarian',
            'removes non-vegetarian meals',
            vegetarian,
            (value) {
              setState(() => vegetarian = value);
              widget
                  .function(Filter(vegetarian, lactoseFree, glutenFree, vegan));
            },
          ),
          buildSwitchListTile(
            'Lactose Free',
            'only includes lactose-free meals',
            lactoseFree,
            (value) {
              setState(() => lactoseFree = value);
              widget
                  .function(Filter(vegetarian, lactoseFree, glutenFree, vegan));
            },
          ),
          buildSwitchListTile(
            'Gluten Free',
            'only includes gluten-free meals',
            glutenFree,
            (value) {
              setState(() => glutenFree = value);
              widget
                  .function(Filter(vegetarian, lactoseFree, glutenFree, vegan));
            },
          ),
          buildSwitchListTile(
            'Vegan',
            'only includes vegan meals',
            vegan,
            (value) {
              setState(() => vegan = value);
              widget
                  .function(Filter(vegetarian, lactoseFree, glutenFree, vegan));
            },
          ),
        ],
      ),
    );
  }
}
