你有两个字符串，即pattern和value。 pattern字符串由字母"a"和"b"组成，用于描述字符串中的模式。例如，字符串"catcatgocatgo"匹配模式"aabab"
（其中"cat"是"a"，"go"是"b"），该字符串也匹配像"a"、"ab"和"b"这样的模式。但需注意"a"和"b"不能同时表示相同的字符串。编写一个方法判断value字
符串是否匹配pattern字符串。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/pattern-matching-lcci
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
class Solution {
    public boolean patternMatching(String pattern, String value) {
        if(pattern.equals("a")||pattern.equals("b")) return true;
        if(pattern.length()==0) return value.length()==0;
        if(value.length()==0){
            boolean ea=false;
            boolean eb=false;
            for(int i=0;i<pattern.length();i++){
                char c=pattern.charAt(i);
                if(c=='a') ea=true;
                else eb=true;

                if(ea&&eb) return false;
            }
            return true;
        }

        char[] parr=pattern.toCharArray();
        char[] varr=value.toCharArray();
        int lenP=parr.length;
        int lenV=varr.length;
        int countA=0;
        int countB=0;
        for(char c:parr){
            if(c=='a') countA++;
            else countB++;
        }
        if(countA==0||countB==0){
            int count=countA+countB;
            int len=lenV/count;
            if(lenV%count!=0) return false;
            for(int i=0;i<lenV;i+=len){
                if(!isEquals(varr,0,i,len)) return false;
            }
            return true;
        }

        for(int lenA=0;lenA<=lenV;lenA++){
            if(lenA*countA>lenV) break;
            int lenB=(lenV-lenA*countA)/countB;
            if(countA*lenA+countB*lenB==lenV){
                boolean isMatch=true;
                int indexA=-1;
                int indexB=-1;
                int index=0;
                for(char c:parr){
                    if(c=='a'){
                        if(indexA==-1) indexA=index;
                        if(!isEquals(varr,index,indexA,lenA)){
                            isMatch=false;
                            break;
                        }
                        index+=lenA;
                    }else{
                        if(indexB==-1) indexB=index;
                        if(!isEquals(varr,index,indexB,lenB)){
                            isMatch=false;
                            break;
                        }
                        index+=lenB;
                    }

                    if(lenA==lenB&&indexA!=-1&&indexB!=-1){
                        if(isEquals(varr,indexA,indexB,lenA)){
                            isMatch=false;
                            break;
                        }
                    }
                }

                if(isMatch) return true;
            }
        }
        return false;
    }

    private boolean isEquals(char[] chars,int i,int j,int len){
        for(int k=0;k<len;k++){
            if(chars[i+k]!=chars[j+k]) return false;
        }
        return true;
    }
}