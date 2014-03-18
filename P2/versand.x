/*
 * versand.x: Remote order protocol
 */
const MAXNAMELEN = 200;

typedef string adress<MAXNAMELEN>;
typedef struct item *itemlist;

struct item {
  int count;
  unsigned int item_number;
  itemlist next;
};

struct order {
  adress adress;
  int customer_number;
  itemlist first;
};

/*
 * The order program definition
 */
program ORDERPROG {
  version ORDERVERS {
    int PLACEORDER(order) = 1;
  } = 1;
} = 0x20150976;
