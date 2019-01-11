import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'tokenizer'
})
export class TokenizerPipe implements PipeTransform {

  transform(value: any, delim?: any): any {
      if (typeof value === "string") {
          if (delim) {
              return value.split("").join(delim);
          }
          return value.split("").join(",");
      } else {
          return value;
      }
  }

}
