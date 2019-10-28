#include <iostream>

void setup(){
    std::cout << "Test if true" << std::endl;
}

void init(int parameter){
    int arrayLen = parameter;
    std::cout << arrayLen << std::endl;
}

bool isTrue(int parameter){
    if (100 < parameter){
        return true;
    }
    else{
        return false;
    }
}



int main(){
    int x = 1000;
    std::cout << isTrue(x) << std::endl;
    return 0;
}


