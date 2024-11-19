#include<iostream>
#include<vector>

using namespace std;

const int N = 1e5+10;
vector<int> g[N];
vector<int>visit(N,0);

bool cycleFind(int source,int p=-1){
    //cout << source << " ";
    visit[source]=1;
    bool isCycleExist = false;
    for(auto child:g[source]){
        if(visit[child] && child==p)continue;
        if(visit[child])return true;
       isCycleExist |= cycleFind(child,source);
    }

    return isCycleExist;
}

int main(){

    int v,e;
    cin >> v >> e;
    for(int i=0;i<e;i++){
        int x,y;
        cin >> x >> y;
        g[x].push_back(y);
        g[y].push_back(x);
    }

   //for(auto x:g[1])cout << x << endl;

   cout << cycleFind(1) << endl;
}