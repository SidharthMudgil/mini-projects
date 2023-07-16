class Transaction {
  final String _id;
  final double _price;
  final String _title;
  final DateTime _date;

  Transaction(this._id, this._price, this._title, this._date);

  String get id => _id;

  DateTime get date => _date;

  String get title => _title;

  double get price => _price;
}