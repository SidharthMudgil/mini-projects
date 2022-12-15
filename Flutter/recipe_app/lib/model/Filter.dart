class Filter {
  final bool _vegetarian;
  final bool _lactoseFree;
  final bool _glutenFree;
  final bool _vegan;

  Filter(this._vegetarian, this._lactoseFree, this._glutenFree, this._vegan);

  bool get vegan => _vegan;

  bool get glutenFree => _glutenFree;

  bool get lactoseFree => _lactoseFree;

  bool get vegetarian => _vegetarian;
}
