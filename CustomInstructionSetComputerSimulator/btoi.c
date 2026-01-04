int btoi(const char* s)
{
    int num = 0;
    s += 2;

    while (*s == '0' || *s == '1'){
        num = (num << 1) | (*s - '0');
        s++;
    }
    return num;
}
