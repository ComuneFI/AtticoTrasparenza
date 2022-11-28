import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { Route, extract } from '@app/core';
import { NotFoundComponent } from './not-found.component';

const routes: Routes = [
  Route.withShell([
    { path: '404', component: NotFoundComponent, data: { title: extract('notFound404') } }
  ])
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
  providers: []
})
export class NotFoundRoutingModule { }
