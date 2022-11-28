import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { Route, extract } from '@app/core';
import { AttiConsiglioComponent } from './atti-consiglio.component';

const routes: Routes = [
  Route.withShell([
    { path: 'atti-consiglio', component: AttiConsiglioComponent, data: { title: extract('councilActs') } }
  ])
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
  providers: []
})
export class AttiConsiglioRoutingModule { }
