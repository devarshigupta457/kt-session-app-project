import { Injectable } from '@angular/core';

export interface CartItem {
  id: number;
  title: string;
  price: String;
  link: string;
}

@Injectable({
  providedIn: 'root'
})
export class CartService {

  private items: CartItem[] = [];

  getItems(): CartItem[] {
    return this.items;
  }

  getCount(): number {
    return this.items.length;
  }

  isInCart(id: number): boolean {
    return this.items.some(i => i.id === id);
  }

  addItem(item: CartItem): void {
    if (!this.isInCart(item.id)) {
      this.items.push(item);
    }
  }

  removeItem(id: number): void {
    this.items = this.items.filter(i => i.id !== id);
  }
}
