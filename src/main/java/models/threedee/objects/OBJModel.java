package main.java.models.threedee.objects;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import main.java.models.threedee.Vector4f;
/**
 * 
 * @author Elwin Slokker
 * @author Benny Bobaganoosh
 * @version 0.1
 */
public class OBJModel
{
    private class OBJIndex
    {
        private int m_vertexIndex;
        private int m_texCoordIndex;
        private int m_normalIndex;

        public int getVertexIndex()
        {
            return m_vertexIndex;
        }

        public int getTexCoordIndex()
        {
            return m_texCoordIndex;
        }

        public int getNormalIndex()
        {
            return m_normalIndex;
        }

        public void setVertexIndex(int val)
        {
            m_vertexIndex = val;
        }

        public void setTexCoordIndex(int val)
        {
            m_texCoordIndex = val;
        }

        public void setNormalIndex(int val)
        {
            m_normalIndex = val;
        }

        @Override
        public boolean equals(Object obj)
        {
            OBJIndex index = (OBJIndex) obj;

            return m_vertexIndex == index.m_vertexIndex
                    && m_texCoordIndex == index.m_texCoordIndex
                    && m_normalIndex == index.m_normalIndex;
        }

        @Override
        public int hashCode()
        {
            final int BASE = 17;
            final int MULTIPLIER = 31;

            int result = BASE;

            result = MULTIPLIER * result + m_vertexIndex;
            result = MULTIPLIER * result + m_texCoordIndex;
            result = MULTIPLIER * result + m_normalIndex;

            return result;
        }
    }

    private List<Vector4f> m_positions;
    private List<Vector4f> m_texCoords;
    private List<Vector4f> m_normals;
    private List<OBJIndex> m_indices;
    private boolean m_hasTexCoords;
    private boolean m_hasNormals;

    private static String[] removeEmptyStrings(String[] data)
    {
        List<String> result = new ArrayList<String>();

        for (int i = 0; i < data.length; i++)
        {
            if (!data[i].equals(""))
            {
                result.add(data[i]);
            }
        }

        String[] res = new String[result.size()];
        result.toArray(res);

        return res;
    }

    public OBJModel(String fileName) throws IOException
    {
        m_positions = new ArrayList<Vector4f>();
        m_texCoords = new ArrayList<Vector4f>();
        m_normals = new ArrayList<Vector4f>();
        m_indices = new ArrayList<OBJIndex>();
        m_hasTexCoords = false;
        m_hasNormals = false;

        BufferedReader meshReader = null;

        meshReader = new BufferedReader(new FileReader(fileName));
        String line;

        while ((line = meshReader.readLine()) != null)
        {
            String[] tokens = line.split(" ");
            tokens = removeEmptyStrings(tokens);

            if (tokens.length == 0 || tokens[0].equals("#"))
            {
                continue;
            } else if (tokens[0].equals("v"))
            {
                m_positions.add(new Vector4f(Float.valueOf(tokens[1]),
                        Float.valueOf(tokens[2]),
                        Float.valueOf(tokens[3]), 1));
            } else if (tokens[0].equals("vt"))
            {
                m_texCoords.add(new Vector4f(Float.valueOf(tokens[1]),
                        1.0f - Float.valueOf(tokens[2]), 0, 0));
            } else if (tokens[0].equals("vn"))
            {
                m_normals.add(new Vector4f(Float.valueOf(tokens[1]),
                        Float.valueOf(tokens[2]),
                        Float.valueOf(tokens[3]), 0));
            } else if (tokens[0].equals("f"))
            {
                for (int i = 0; i < tokens.length - 3; i++)
                {
                    m_indices.add(parseOBJIndex(tokens[1]));
                    m_indices.add(parseOBJIndex(tokens[2 + i]));
                    m_indices.add(parseOBJIndex(tokens[3 + i]));
                }
            }
        }

        meshReader.close();
    }

    public IndexedModel toIndexedModel()
    {
        IndexedModel result = new IndexedModel();
        IndexedModel normalModel = new IndexedModel();
        Map<OBJIndex, Integer> resultIndexMap = new HashMap<OBJIndex, Integer>();
        Map<Integer, Integer> normalIndexMap = new HashMap<Integer, Integer>();
        Map<Integer, Integer> indexMap = new HashMap<Integer, Integer>();

        for (int i = 0; i < m_indices.size(); i++)
        {
            OBJIndex currentIndex = m_indices.get(i);

            Vector4f currentPosition = m_positions.get(currentIndex.getVertexIndex());
            Vector4f currentTexCoord;
            Vector4f currentNormal;

            if (m_hasTexCoords)
            {
                currentTexCoord = m_texCoords.get(currentIndex.getTexCoordIndex());
            } else
            {
                currentTexCoord = new Vector4f(0, 0, 0, 0);
            }

            if (m_hasNormals)
            {
                currentNormal = m_normals.get(currentIndex.getNormalIndex());
            } else
            {
                currentNormal = new Vector4f(0, 0, 0, 0);
            }

            Integer modelVertexIndex = resultIndexMap.get(currentIndex);

            if (modelVertexIndex == null)
            {
                modelVertexIndex = result.getPositions().size();
                resultIndexMap.put(currentIndex, modelVertexIndex);

                result.getPositions().add(currentPosition);
                result.getTexCoords().add(currentTexCoord);
                if (m_hasNormals)
                {
                    result.getNormals().add(currentNormal);
                }
            }

            Integer normalModelIndex = normalIndexMap.get(currentIndex.getVertexIndex());

            if (normalModelIndex == null)
            {
                normalModelIndex = normalModel.getPositions().size();
                normalIndexMap.put(currentIndex.getVertexIndex(), normalModelIndex);

                normalModel.getPositions().add(currentPosition);
                normalModel.getTexCoords().add(currentTexCoord);
                normalModel.getNormals().add(currentNormal);
                normalModel.getTangents().add(new Vector4f(0, 0, 0, 0));
            }

            result.getIndices().add(modelVertexIndex);
            normalModel.getIndices().add(normalModelIndex);
            indexMap.put(modelVertexIndex, normalModelIndex);
        }

        if (!m_hasNormals)
        {
            normalModel.calcNormals();

            for (int i = 0; i < result.getPositions().size(); i++)
            {
                result.getNormals().add(normalModel.getNormals().get(indexMap.get(i)));
            }
        }

        normalModel.calcTangents();

        for (int i = 0; i < result.getPositions().size(); i++)
        {
            result.getTangents().add(normalModel.getTangents().get(indexMap.get(i)));
        }

        return result;
    }

    private OBJIndex parseOBJIndex(String token)
    {
        String[] values = token.split("/");

        OBJIndex result = new OBJIndex();
        result.setVertexIndex(Integer.parseInt(values[0]) - 1);

        if (values.length > 1)
        {
            if (!values[1].isEmpty())
            {
                m_hasTexCoords = true;
                result.setTexCoordIndex(Integer.parseInt(values[1]) - 1);
            }

            if (values.length > 2)
            {
                m_hasNormals = true;
                result.setNormalIndex(Integer.parseInt(values[2]) - 1);
            }
        }

        return result;
    }
}
