import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {Observable} from "rxjs/index";
import { ApiResponse } from '../model/api.response';
import { Item } from '../model/item.model';
import { SearchModel } from '../model/search.model';

@Injectable()
export class ItemService {

  constructor(private http : HttpClient) { }
  private baseUrl: string = 'http://localhost:8080/api/items/';

  getItems(status: string) : Observable<ApiResponse> {
    return this.http.get<ApiResponse>(this.baseUrl + 'list/' + status );
  }

  getItemById(id: number): Observable<any> {
    return this.http.get(this.baseUrl + id);
  }

  createItem(item: Item): Observable<ApiResponse> {
    return this.http.post<ApiResponse>(this.baseUrl, item);
  }

  updateItem(id: number, item: Item): Observable<ApiResponse> {
    return this.http.put<ApiResponse>(this.baseUrl + id, item);
  }

  deleteItem(id: number): Observable<ApiResponse> {
    return this.http.delete<ApiResponse>(this.baseUrl + id);
  }

  customSearch(searchModel : SearchModel): Observable<ApiResponse> {
    return this.http.post<ApiResponse>(this.baseUrl + "search", searchModel);
  }
}
